package com.web.backend.application.DTO.Auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
    private String username;
    private String email;
    private String password;
}
