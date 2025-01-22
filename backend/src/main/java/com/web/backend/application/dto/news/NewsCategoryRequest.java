package com.web.backend.application.DTO.news;

import jakarta.validation.constraints.NotBlank;

public record NewsCategoryRequest(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name
) {
}