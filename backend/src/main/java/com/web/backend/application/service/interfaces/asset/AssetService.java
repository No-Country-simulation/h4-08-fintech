package com.web.backend.application.service.interfaces.asset;

import com.web.backend.application.DTO.asset.AssetCreateRequest;
import com.web.backend.application.DTO.asset.AssetUpdateRequest;
import com.web.backend.application.DTO.asset.AssetResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssetService {
    List<AssetResponse> getAssets(boolean deleted, String keyword, String sector);

    AssetResponse createAsset(AssetCreateRequest request);

    List<AssetResponse> createAssetsWithCsv(MultipartFile file);

    AssetResponse getAssetById(String ticker);

    AssetResponse updateAsset(String ticker, AssetUpdateRequest assetRequest);

    void deleteAsset(String ticker);
}
