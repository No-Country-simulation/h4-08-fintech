package com.web.backend.infrastructure.api.utils.auth;

import lombok.Builder;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CreateCookie {

    @Builder
    public static ResponseCookie auth(String token){

        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(10 * 60 * 60)
                .sameSite("Lax")
                .build();
    }
    @Builder
    public static ResponseCookie cookie(String name, String value){

        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(10 * 60 * 60)
                .sameSite("Lax")
                .build();
    }


}
