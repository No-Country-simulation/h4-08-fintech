package com.web.backend.infrastructure.api.utils.asset;

import com.web.backend.application.DTO.AssetResponse;
import com.web.backend.domain.model.asset.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetResponse toAssetResponse(Asset asset);
}
