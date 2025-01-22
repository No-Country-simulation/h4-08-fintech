package com.web.backend.infrastructure.api.utils.asset;

import com.web.backend.application.DTO.asset.AssetTypeRequest;
import com.web.backend.application.DTO.asset.AssetTypeResponse;
import com.web.backend.domain.model.asset.AssetType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AssetTypeMapper {
    AssetType toAssetType(AssetTypeRequest assetTypeRequest);

    AssetTypeResponse toAssetTypeResponse(AssetType assetType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAssetTypeFromRequest(AssetTypeRequest assetTypeRequest, @MappingTarget AssetType assetType);
}
