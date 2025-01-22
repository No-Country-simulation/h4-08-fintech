package com.web.backend.application.DTO.asset;

import java.time.LocalDateTime;

public record AssetRequest(
    Float price,
    Long assetTypeId,
    LocalDateTime updatedAt
) {
}