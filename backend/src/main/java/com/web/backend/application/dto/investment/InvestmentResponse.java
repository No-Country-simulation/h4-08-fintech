package com.web.backend.application.dto.investment;

import java.util.Date;

public record InvestmentResponse(
        Long id,
//            Customer customer;
        InvestmentTypeResponse investmentType,
        Float amount,
        Date investmentDate,
        Date maturityDate,
        Float currentValue,
        String status
) {
}
