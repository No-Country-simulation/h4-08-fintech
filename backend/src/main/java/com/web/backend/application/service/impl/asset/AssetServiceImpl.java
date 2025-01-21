package com.web.backend.application.service.impl.asset;

import com.web.backend.application.DTO.AssetResponse;
import com.web.backend.application.service.interfaces.asset.AssetService;
import com.web.backend.domain.model.asset.Asset;
import com.web.backend.domain.repository.asset.AssetRepository;
import com.web.backend.infrastructure.api.utils.asset.AssetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public List<AssetResponse> getAllAssetsByDeleted(boolean deleted) {
        List<Asset> assets = assetRepository.findAssetsByDeleted(deleted);
        return assets.stream().map(assetMapper::toAssetResponse).toList();
    }

    @Override
    public AssetResponse importAsset(String ticker) {
        return null;
    }
}
