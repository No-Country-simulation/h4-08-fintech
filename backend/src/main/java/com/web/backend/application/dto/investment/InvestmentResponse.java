package com.web.backend.application.dto.investment;

import java.time.LocalDateTime;

public record InvestmentResponse(
        Long id,
//            Customer customer;
        InvestmentTypeResponse investmentType,
        Float amount,
        LocalDateTime investmentDate,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
