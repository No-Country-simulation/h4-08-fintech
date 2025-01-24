package com.web.backend.application.DTO.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AssetCreateRequest(
        @NotBlank(message = "El ticker no puede estar en blanco")
        String ticker,
        @NotNull(message = "El assert type id no puede ser null")
        Long assetTypeId
) {
}