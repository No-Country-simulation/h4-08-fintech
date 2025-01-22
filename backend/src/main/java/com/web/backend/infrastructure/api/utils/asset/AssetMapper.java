package com.web.backend.infrastructure.api.utils.asset;

import com.web.backend.application.DTO.asset.AssetRequest;
import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.domain.model.asset.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetResponse toAssetResponse(Asset asset);
    void updateAssetFromRequest(AssetRequest assetRequest, @MappingTarget Asset asset);
}