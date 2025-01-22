package com.web.backend.application.DTO.investment;

import java.time.LocalDateTime;

public record InvestmentResponse(
        Long id,
//            Customer customer;
        Float amount,
        LocalDateTime investmentDate,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
