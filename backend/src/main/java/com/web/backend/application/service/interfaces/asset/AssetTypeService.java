package com.web.backend.application.service.interfaces.asset;

import com.web.backend.application.dto.asset.AssetTypeRequest;
import com.web.backend.application.dto.asset.AssetTypeResponse;

import java.util.List;

public interface AssetTypeService {
    AssetTypeResponse createAssetType(AssetTypeRequest AssetTypeRequest);

    AssetTypeResponse getAssetTypeById(Long id);

    List<AssetTypeResponse> getAssetTypesByDeleted(boolean deleted);

    AssetTypeResponse updateAssetType(Long id, AssetTypeRequest InvestmentTypeDTO);

    void deleteAssetType(Long id);
}
