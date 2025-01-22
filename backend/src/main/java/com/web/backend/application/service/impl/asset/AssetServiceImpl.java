package com.web.backend.application.service.impl.asset;

import com.web.backend.application.DTO.asset.AssetRequest;
import com.web.backend.application.DTO.alphavantage.GlobalQuoteResponse;
import com.web.backend.application.DTO.alphavantage.GlobalQuote;
import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.service.interfaces.asset.AssetService;
import com.web.backend.domain.model.asset.Asset;
import com.web.backend.domain.repository.asset.AssetRepository;
import com.web.backend.domain.repository.asset.AssetTypeRepository;
import com.web.backend.infrastructure.api.external.AlphaVantageClient;
import com.web.backend.infrastructure.api.utils.asset.AssetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetTypeRepository assetTypeRepository;
    private final AssetMapper assetMapper;
    private final AlphaVantageClient alphavantageClient;

    @Override
    public List<AssetResponse> getAllAssetsByDeleted(boolean deleted) {
        List<Asset> assets = assetRepository.findAssetsByDeleted(deleted);
        return assets.stream().map(assetMapper::toAssetResponse).toList();
    }

    @Override
    public AssetResponse importAsset(String function, String ticker, String apikey) {
        GlobalQuoteResponse globalQuoteResponse = alphavantageClient.getGlobalQuote(function, ticker, apikey);
        GlobalQuote globalQuote = globalQuoteResponse.globalQuote();

        Asset asset = Asset.builder()
                .ticker(globalQuote.symbol())
                .price(globalQuote.price())
                .updatedAt(globalQuote.latestTradingDay().atStartOfDay())
                .build();
        assetRepository.save(asset);

        return assetMapper.toAssetResponse(asset);
    }

    @Override
    public AssetResponse getAssetById(String ticker) {
        Asset asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));
        return assetMapper.toAssetResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(String ticker, AssetRequest assetRequest) {
        Asset asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));

        if(assetRequest.assetTypeId() != null) {
            assetTypeRepository.findById(assetRequest.assetTypeId())
                    .orElseThrow(() -> new AssetNotFoundException("Asset type not found with id: " + assetRequest.assetTypeId()));
        }
        assetMapper.updateAssetFromRequest(assetRequest, asset);
        Asset updatedAsset = assetRepository.save(asset);

        return assetMapper.toAssetResponse(updatedAsset);
    }

    @Override
    public void deleteAsset(String ticker) {
        Asset asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));
        assetRepository.delete(asset);
    }
}
