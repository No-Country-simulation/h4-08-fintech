package com.web.backend.application.dto.auth;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
    private String username;
    private String email;
    @Nullable
    private String password;
}
