package com.web.backend.application.dtos.news;

import jakarta.validation.constraints.NotBlank;

public record NewsSourceRequest(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name
) {
}