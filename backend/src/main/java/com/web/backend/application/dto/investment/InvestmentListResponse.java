package com.web.backend.application.dto.investment;

import com.web.backend.application.dto.asset.AssetResponse;
import com.web.backend.domain.model.assetTemp.AssetTemp;

import java.time.LocalDateTime;

public record InvestmentListResponse(
        Long id,
//            Customer customer;
        Float amount,
        AssetTemp asset,
        LocalDateTime investmentDate,
        LocalDateTime maturityDate,
        Float currentValue,
        String status
) {
}
