package com.web.backend.infrastructure.api.controller.Auth;

import com.web.backend.infrastructure.api.utils.auth.CreateCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class AuthGoogleController {

    @GetMapping("/success")
    public ResponseEntity<?> success(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String token = (String) oAuth2User.getAttributes().get("token");

        ResponseCookie cookie = CreateCookie.auth(token);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Google Login successful. JWT generated.");
    }
}
