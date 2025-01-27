package com.web.backend.application.DTO.asset;

import java.time.LocalDateTime;

public record AssetResponse(
        String ticker,
        String name,
        Float price,
        AssetTypeResponse assetType,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
