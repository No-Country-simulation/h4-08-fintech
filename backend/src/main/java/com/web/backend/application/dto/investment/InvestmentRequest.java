package com.web.backend.application.DTO.investment;

import com.web.backend.application.DTO.asset.AssetResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InvestmentRequest(
        @NotNull(message = "El cliente no puede ser null")
        Long customerId,
        @NotBlank(message = "El asset no puede estar en blanco")
        String assetId,
        Float amount,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
