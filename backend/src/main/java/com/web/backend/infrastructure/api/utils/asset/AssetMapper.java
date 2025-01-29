package com.web.backend.infrastructure.api.utils.asset;

import com.web.backend.application.dtos.asset.AssetCreateRequest;
import com.web.backend.application.dtos.asset.AssetUpdateRequest;
import com.web.backend.application.dtos.asset.AssetResponse;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetTemp toAsset(AssetCreateRequest assetRequest);

    AssetResponse toAssetResponse(AssetTemp asset);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAssetFromRequest(AssetUpdateRequest assetRequest, @MappingTarget AssetTemp asset);
}