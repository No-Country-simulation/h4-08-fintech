package com.web.backend.application.DTO;

import java.time.LocalDateTime;

public record AssetResponse(
        String ticker,
        Float price,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
