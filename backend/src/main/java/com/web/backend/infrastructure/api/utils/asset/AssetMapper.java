package com.web.backend.infrastructure.api.utils.asset;

import com.web.backend.application.DTO.asset.AssetCreateRequest;
import com.web.backend.application.DTO.asset.AssetUpdateRequest;
import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.domain.model.asset.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    Asset toAsset(AssetCreateRequest assetRequest);
    AssetResponse toAssetResponse(Asset asset);
    void updateAssetFromRequest(AssetUpdateRequest assetRequest, @MappingTarget Asset asset);
}