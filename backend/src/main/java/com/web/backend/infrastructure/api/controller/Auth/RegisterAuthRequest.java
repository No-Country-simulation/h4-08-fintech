package com.web.backend.infrastructure.api.controller.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class RegisterAuthRequest {
    private String email;
    private String username;
    private String password;
}
