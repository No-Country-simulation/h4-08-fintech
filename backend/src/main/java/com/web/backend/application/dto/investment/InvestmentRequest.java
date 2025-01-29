package com.web.backend.application.dto.investment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InvestmentRequest(
        @NotNull(message = "El cliente no puede ser null")
        Long customerId,
        @NotBlank(message = "El asset no puede estar en blanco")
        String assetId,
        @NotNull(message = "La cantidad no puede ser null")
        @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que 0")
        Float amount,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
