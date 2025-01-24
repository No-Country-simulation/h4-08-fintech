package com.web.backend.infrastructure.api.utils.auth;

import org.springframework.http.ResponseCookie;

public class CreateCookie {

    private static final boolean HTTP_ONLY = Boolean.parseBoolean(System.getenv("COOKIE_HTTP_ONLY"));
    private static final boolean SECURE = Boolean.parseBoolean(System.getenv("COOKIE_SECURE"));
    private static final String SAME_SITE = System.getenv("COOKIE_SAME_SITE") != null ? System.getenv("COOKIE_SAME_SITE") : "Lax";

    public static ResponseCookie auth(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(HTTP_ONLY)
                .secure(SECURE)
                .path("/")
                .maxAge(10 * 60 * 60)
                .sameSite(SAME_SITE)
                .build();
    }

    public static ResponseCookie cookie(String name, String value) {
        return ResponseCookie.from(name, value)
                .httpOnly(HTTP_ONLY)
                .secure(SECURE)
                .path("/")
                .maxAge(10 * 60 * 60)
                .sameSite(SAME_SITE)
                .build();
    }
}
