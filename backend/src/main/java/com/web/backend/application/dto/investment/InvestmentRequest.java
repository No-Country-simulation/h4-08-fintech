package com.web.backend.application.dto.investment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InvestmentRequest(
        @NotNull(message = "El cliente no puede estar en blanco")
        Long customerId,
        Long investmentTypeId,
        Float amount,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
