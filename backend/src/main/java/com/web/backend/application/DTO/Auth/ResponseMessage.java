package com.web.backend.application.DTO.Auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseMessage {
    String email;
    String username;
}
