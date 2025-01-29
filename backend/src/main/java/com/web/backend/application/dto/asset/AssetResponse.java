package com.web.backend.application.dto.asset;

import java.time.LocalDateTime;

public record AssetResponse(
        String tickerSymbol,
        String assetName,
        String assetTypeApi,
        AssetTypeResponse assetType,
        String sector,
        int riskLevel,
        double currentPrice,
        float potentialReturns,
        String currency,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
