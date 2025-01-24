package com.web.backend.application.service.impl.asset;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.web.backend.application.DTO.asset.AssetCreateRequest;
import com.web.backend.application.DTO.asset.AssetUpdateRequest;
import com.web.backend.application.DTO.alphavantage.GlobalQuoteResponse;
import com.web.backend.application.DTO.alphavantage.GlobalQuote;
import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.exception.asset.AssetTypeNotFoundException;
import com.web.backend.application.exception.asset.InvalidTickerException;
import com.web.backend.application.service.interfaces.asset.AssetService;
import com.web.backend.domain.model.asset.Asset;
import com.web.backend.domain.model.asset.AssetType;
import com.web.backend.domain.repository.asset.AssetRepository;
import com.web.backend.domain.repository.asset.AssetTypeRepository;
import com.web.backend.infrastructure.api.external.AlphaVantageClient;
import com.web.backend.infrastructure.api.utils.asset.AssetMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetTypeRepository assetTypeRepository;
    private final AssetMapper assetMapper;
    private final AlphaVantageClient alphavantageClient;
    private final String apikey = System.getenv("API_KEY");

    @Override
    public List<AssetResponse> getAllAssetsByDeleted(boolean deleted) {
        List<Asset> assets = assetRepository.findAssetsByDeleted(deleted);
        return assets.stream().map(assetMapper::toAssetResponse).toList();
    }

    @Override
    @Transactional
    public AssetResponse createAsset(AssetCreateRequest assetRequest) {
        Asset asset = assetMapper.toAsset(assetRequest);

        AssetType assetType = assetTypeRepository.findById(assetRequest.assetTypeId())
                .orElseThrow(() -> new AssetTypeNotFoundException("Asset type not found with id: " +
                        assetRequest.assetTypeId()));
        asset.setAssetType(assetType);
        importAssetData(asset);

        assetRepository.save(asset);
        return assetMapper.toAssetResponse(asset);
    }

    public List<AssetResponse> createAssetsWithCsv(MultipartFile file) {
        List<Asset> createdAssets = new ArrayList<>();

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();
            if (!records.isEmpty()) {
                records.remove(0); // Remove header row if present
            }

            for (String[] record : records) {
                AssetType assetType = assetTypeRepository.findById(Long.valueOf(record[1]))
                        .orElseThrow(() -> new AssetTypeNotFoundException("Asset type not found with id: " + record[1]));
                Asset asset = Asset.builder()
                        .ticker(record[0])
                        .assetType(assetType)
                        .build();
                importAssetData(asset);
                assetRepository.save(asset);
                createdAssets.add(asset);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error creating assets from CSV file", e);
        }

        return createdAssets.stream().map(assetMapper::toAssetResponse).toList();
    }


    // Add missing data from API AlphaVantage into asset
    private void importAssetData(Asset asset) {
        GlobalQuoteResponse globalQuoteResponse = alphavantageClient.getGlobalQuote("GLOBAL_QUOTE", asset.getTicker(), apikey);
        GlobalQuote globalQuote = globalQuoteResponse.globalQuote();

        if(globalQuote.symbol() == null)
            throw new InvalidTickerException("Invalid ticker: " + asset.getTicker());

        asset.setPrice(globalQuote.price());
        asset.setUpdatedAt(globalQuote.latestTradingDay().atStartOfDay());
    }

    @Override
    public AssetResponse getAssetById(String ticker) {
        Asset asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));
        return assetMapper.toAssetResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(String ticker, AssetUpdateRequest assetRequest) {
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
