package com.web.backend.config;

import com.web.backend.application.service.Auth.CustomOAuth2UserService;
import com.web.backend.config.filter.AuthFilter;
import com.web.backend.infrastructure.api.utils.auth.AESUtil;
import com.web.backend.infrastructure.api.utils.auth.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtil jwtUtil;
    private final AESUtil aesUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        System.out.println("AuthenticationManager initialized");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        System.out.println("Initializing CustomOAuth2UserService with JwtTokenUtil: " + jwtUtil);
        return new CustomOAuth2UserService(jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/api/v*/registration/**",
                                "/actuator/**",
                                "/oauth2/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService())
                        )
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth2/authorize")
                        )
                        .redirectionEndpoint(redis -> redis
                                .baseUri("/oauth2/callback/*")
                        )
                        .successHandler((request, response, authentication) -> {
                            try {
                                DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
                                String email = oauthUser.getAttribute("email");
                                String name = oauthUser.getAttribute("name");
                                String token = jwtUtil.generateToken(email);

                                assert email != null && token != null && name != null;

                                String encryptedEmail = URLEncoder.encode(aesUtil.encrypt(email), StandardCharsets.UTF_8);
                                String encryptedToken = URLEncoder.encode(aesUtil.encrypt(token), StandardCharsets.UTF_8);
                                String encryptedName = URLEncoder.encode(aesUtil.encrypt(name), StandardCharsets.UTF_8);

                                response.sendRedirect("/oauth2/success?token=" + encryptedToken + "&email=" + encryptedEmail + "&name=" + encryptedName);
                            } catch (Exception e) {
                                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Authentication failed");
                            }
                        })
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("{\"error\": \"Login failed\", \"message\": \"" + exception.getMessage() + "\"}");
                            response.getWriter().flush();
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    private void handleOAuth2Success(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
            String email = oauthUser.getAttribute("email");
            String name = oauthUser.getAttribute("name");
            String token = jwtUtil.generateToken(email);

            if (email == null || token == null || name == null) {
                throw new IllegalArgumentException("Missing required attributes in OAuth2 response");
            }

            String encryptedEmail = URLEncoder.encode(aesUtil.encrypt(email), StandardCharsets.UTF_8);
            String encryptedToken = URLEncoder.encode(aesUtil.encrypt(token), StandardCharsets.UTF_8);
            String encryptedName = URLEncoder.encode(aesUtil.encrypt(name), StandardCharsets.UTF_8);

            response.sendRedirect("/oauth2/success?token=" + encryptedToken + "&email=" + encryptedEmail + "&name=" + encryptedName);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error processing OAuth2 login");
            } catch (IOException ioException) {
                System.out.println("Error writing error response"+ ioException);
            }
        }
    }

}

