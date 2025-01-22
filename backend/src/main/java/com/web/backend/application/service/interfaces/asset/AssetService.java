package com.web.backend.application.service.interfaces.asset;

import com.web.backend.application.DTO.AssetResponse;

import java.util.List;

public interface AssetService {

    List<AssetResponse> getAllAssetsByDeleted(boolean deleted);

    AssetResponse importAsset(String function,String ticker, String apikey);
}
