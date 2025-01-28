package com.web.backend.application.DTO.asset;

import java.time.LocalDateTime;

public record AssetResponse(
        String tickerSymbol,
        String assetName,
        String assetType,
        //        AssetTypeResponse assetType,
        String sector,
        int riskLevel,
        double currentPrice,
        float potentialReturns,
        String currency,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
