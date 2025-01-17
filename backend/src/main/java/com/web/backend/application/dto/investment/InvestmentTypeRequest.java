package com.web.backend.application.dto.investment;

import jakarta.validation.constraints.NotBlank;

public record InvestmentTypeRequest(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name
) {
}
