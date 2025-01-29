package com.web.backend.application.dto.asset;

import jakarta.validation.constraints.NotBlank;

public record AssetTypeRequest(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name
) {
}
