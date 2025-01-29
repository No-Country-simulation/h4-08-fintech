package com.web.backend.application.dtos.asset;

import java.time.LocalDateTime;

public record AssetUpdateRequest(
        String ticker,
        String name,
        Float price,
        Long assetTypeId,
        LocalDateTime updatedAt
) {
}