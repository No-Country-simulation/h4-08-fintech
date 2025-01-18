package com.web.backend.application.service.Auth;

import com.web.backend.application.DTO.Auth.LoginDto;
import com.web.backend.application.DTO.User.PublicUserDto;
import com.web.backend.application.service.User.UserService;
import com.web.backend.infrastructure.api.utils.auth.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final JwtTokenUtil jwtUtil;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("Cargando datos del usuario desde el proveedor OAuth2...");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("Datos del usuario recibidos: " + oAuth2User.getAttributes());

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null || name == null) {
            throw new OAuth2AuthenticationException("El email o nombre no están presentes en los atributos del usuario.");
        }

        String token = jwtUtil.generateToken(email);
        System.out.println("JWT generado: " + token);

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("token", token);

        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "email");
    }

}

