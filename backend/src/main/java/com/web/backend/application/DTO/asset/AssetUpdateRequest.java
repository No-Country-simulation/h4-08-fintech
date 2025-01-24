package com.web.backend.application.DTO.asset;

import java.time.LocalDateTime;

public record AssetUpdateRequest(
        String ticker,
        Float price,
        Long assetTypeId,
        LocalDateTime updatedAt
) {
}