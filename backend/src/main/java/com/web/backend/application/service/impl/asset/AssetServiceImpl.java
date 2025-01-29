package com.web.backend.application.service.impl.asset;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.web.backend.application.dto.asset.AssetCreateRequest;
import com.web.backend.application.dto.asset.AssetUpdateRequest;
import com.web.backend.application.dto.asset.AssetResponse;
import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.exception.asset.AssetTypeNotFoundException;
import com.web.backend.application.service.investment.ISInvestmentRecommendationService;
import com.web.backend.application.service.interfaces.asset.AssetService;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import com.web.backend.domain.repository.assetTemp.RAssentTemp;
import com.web.backend.domain.model.asset.AssetType;
import com.web.backend.domain.repository.asset.AssetTypeRepository;
import com.web.backend.infrastructure.api.external.AlphaVantageClient;
import com.web.backend.infrastructure.api.utils.asset.AssetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final RAssentTemp assetRepository;
    private final AssetTypeRepository assetTypeRepository;
    private final AssetMapper assetMapper;
    private final AlphaVantageClient alphavantageClient;
    private final String apikey = System.getenv("API_KEY");
    private final ISInvestmentRecommendationService recommendationService;

    @Override
    public List<AssetResponse> getAssets(boolean deleted, String keyword, String sector) {
        List<AssetTemp> assets;

        if (keyword != null) {
            assets = assetRepository.findByDeletedAndKeyword(deleted, keyword);
        } else if (sector != null) {
            assets = assetRepository.findByDeletedAndSector(deleted, sector);
        } else {
            assets = assetRepository.findByDeleted(deleted);
        }

        return assets.stream()
                .map(assetMapper::toAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AssetResponse createAsset(AssetCreateRequest assetRequest) {
        AssetTemp asset = assetMapper.toAsset(assetRequest);

//        AssetType assetType = assetTypeRepository.findById(assetRequest.assetTypeId())
//                .orElseThrow(() -> new AssetTypeNotFoundException("Asset type not found with id: " +
//                        assetRequest.assetTypeId()));
//        asset.setAssetType(assetType);

        assetRepository.save(asset);
        return assetMapper.toAssetResponse(asset);
    }

    public List<AssetResponse> createAssetsWithCsv(MultipartFile file) {
        List<AssetTemp> createdAssets = new ArrayList<>();

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();
            if (!records.isEmpty()) {
                records.remove(0); // Remove header row if present
            }

            for (String[] record : records) {
                AssetTemp asset;
                long assetTypeId = Long.valueOf(record[1]);

                // If asset type is ETF or stock
                if(assetTypeId == 0 || assetTypeId == 1) {
                    recommendationService.populateAssetsByKeywordFromApi(record[0], 1);
                    asset = assetRepository.findById(record[0])
                            .orElseThrow(() -> new AssetNotFoundException("Asset not found with id: " + record[0]));
                }
                else {
                    asset = AssetTemp.builder()
                            .tickerSymbol(record[0])
                            .assetName(record[2])
                            .assetTypeApi(record[3])
                            .sector(record[4])
                            .riskLevel(Integer.parseInt(record[5]))
                            .currentPrice(Double.parseDouble(record[6]))
                            .potentialReturns(Float.parseFloat(record[7]))
                            .currency(record[8])
                            .build();
                }

                AssetType assetType = assetTypeRepository.findById(assetTypeId)
                        .orElseThrow(() -> new AssetTypeNotFoundException("Asset type not found with id: " + assetTypeId));
                asset.setAssetType(assetType);

                assetRepository.save(asset);
                createdAssets.add(asset);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error creating assets from CSV file", e);
        }

        return createdAssets.stream().map(assetMapper::toAssetResponse).toList();
    }


    @Override
    public AssetResponse getAssetById(String ticker) {
        AssetTemp asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));
        return assetMapper.toAssetResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(String ticker, AssetUpdateRequest assetRequest) {
        AssetTemp asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));

        if(assetRequest.assetTypeId() != null) {
            assetTypeRepository.findById(assetRequest.assetTypeId())
                    .orElseThrow(() -> new AssetTypeNotFoundException("Asset type not found with id: " + assetRequest.assetTypeId()));
        }
        assetMapper.updateAssetFromRequest(assetRequest, asset);
        AssetTemp updatedAsset = assetRepository.save(asset);

        return assetMapper.toAssetResponse(updatedAsset);
    }

    @Override
    public void deleteAsset(String ticker) {
        AssetTemp asset = assetRepository.findById(ticker)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with ticker: " + ticker));
        assetRepository.delete(asset);
    }
}
