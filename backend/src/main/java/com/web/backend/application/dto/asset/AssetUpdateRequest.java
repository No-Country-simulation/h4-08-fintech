package com.web.backend.application.dto.asset;

import java.time.LocalDateTime;

public record AssetUpdateRequest(
        String assetName,
        String assetTypeApi,
        Long assetTypeId,
        String sector,
        Integer riskLevel,
        Double currentPrice,
        Float potentialReturns,
        String currency,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}