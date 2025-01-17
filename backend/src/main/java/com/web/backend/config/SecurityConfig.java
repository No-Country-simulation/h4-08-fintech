package com.web.backend.config;

import com.web.backend.application.service.Auth.CustomOAuth2UserService;
import com.web.backend.config.filter.AuthFilter;
import com.web.backend.infrastructure.api.utils.auth.AESUtil;
import com.web.backend.infrastructure.api.utils.auth.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtil jwtUtil;
    private final AESUtil aesUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        System.out.println("Inicializando CustomOAuth2UserService con JwtTokenUtil: " + jwtUtil);
        return new CustomOAuth2UserService(jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/api/v*/registration/**",
                                "/auth/register*",
                                "/auth/login",
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

                                System.out.println("Email encriptado: " + encryptedEmail);
                                System.out.println("Token encriptado: " + encryptedToken);

                                // Redirige con los datos encriptados
                                response.sendRedirect("/oauth2/success?token=" + encryptedToken + "&email=" + encryptedEmail+"&name=" + encryptedName);

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }


                        })

                        .failureHandler((request, response, exception) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login failed");
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
