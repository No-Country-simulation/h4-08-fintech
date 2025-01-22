package com.web.backend.application.DTO.asset;

import java.time.LocalDateTime;

public record AssetResponse(
        String ticker,
        Float price,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
