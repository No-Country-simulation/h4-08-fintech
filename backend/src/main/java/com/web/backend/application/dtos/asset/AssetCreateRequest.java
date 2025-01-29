package com.web.backend.application.dtos.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetCreateRequest(
        @NotBlank(message = "El ticker no puede estar en blanco")
        String ticker,
        @NotNull(message = "El assert type id no puede ser null")
        Long assetTypeId
) {
}