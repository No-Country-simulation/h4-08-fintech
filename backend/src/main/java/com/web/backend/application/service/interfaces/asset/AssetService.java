package com.web.backend.application.service.interfaces.asset;

import com.web.backend.application.DTO.asset.AssetRequest;
import com.web.backend.application.DTO.asset.AssetResponse;

import java.util.List;

public interface AssetService {
    List<AssetResponse> getAllAssetsByDeleted(boolean deleted);
    AssetResponse importAsset(String function, String ticker, String apikey);
    AssetResponse getAssetById(String ticker);
    AssetResponse updateAsset(String ticker, AssetRequest assetRequest);
    void deleteAsset(String ticker);
}
