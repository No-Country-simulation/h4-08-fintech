package com.web.backend.application.dto.asset;

import java.time.LocalDateTime;

public record AssetUpdateRequest(
        String ticker,
        String name,
        Float price,
        Long assetTypeId,
        LocalDateTime updatedAt
) {
}