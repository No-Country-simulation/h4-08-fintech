package com.web.backend.application.dto.investment;

import com.web.backend.application.dto.asset.AssetResponse;

import java.time.LocalDateTime;

public record InvestmentResponse(
        Long id,
//            Customer customer;
        Float amount,
        AssetResponse asset,
        LocalDateTime investmentDate,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
