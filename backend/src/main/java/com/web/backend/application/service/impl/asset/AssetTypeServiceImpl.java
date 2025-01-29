package com.web.backend.application.service.impl.asset;

import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.application.DTO.asset.AssetTypeRequest;
import com.web.backend.application.DTO.asset.AssetTypeResponse;
import com.web.backend.application.exception.asset.AssetTypeNotFoundException;
import com.web.backend.application.service.interfaces.asset.AssetTypeService;
import com.web.backend.domain.model.AssetTemp.AssetTemp;
import com.web.backend.domain.model.asset.AssetType;
import com.web.backend.domain.repository.AssetTemp.RAssentTemp;
import com.web.backend.domain.repository.asset.AssetTypeRepository;
import com.web.backend.infrastructure.api.utils.asset.AssetMapper;
import com.web.backend.infrastructure.api.utils.asset.AssetTypeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetTypeServiceImpl implements AssetTypeService {
    
    private final AssetTypeRepository assetTypeRepository;
    private final RAssentTemp assetRepository;
    private final AssetTypeMapper assetTypeMapper;
    private final AssetMapper assetMapper;

    @Override
    public AssetTypeResponse createAssetType(AssetTypeRequest assetTypeRequest) {
        AssetType assetType = assetTypeMapper.toAssetType(assetTypeRequest);
        assetTypeRepository.save(assetType);
        return assetTypeMapper.toAssetTypeResponse(assetType);
    }

    @Override
    public AssetTypeResponse getAssetTypeById(Long id) {
        AssetType assetType = assetTypeRepository.findById(id)
                .orElseThrow(() -> new AssetTypeNotFoundException("Asset Type not found with id: " + id));
        if (assetType.isDeleted()) {
            throw new IllegalStateException("The Asset type with id: " + id + " has been deleted.");
        }
        return assetTypeMapper.toAssetTypeResponse(assetType);
    }

    @Override
    public List<AssetTypeResponse> getAssetTypesByDeleted(boolean deleted) {
        List<AssetType> assetTypes = assetTypeRepository.findAllByIsDeleted(deleted);
        return assetTypes.stream().map(assetTypeMapper::toAssetTypeResponse).toList();
    }

    @Override
    public AssetTypeResponse updateAssetType(Long id, AssetTypeRequest assetTypeRequest) {
        AssetType assetType = assetTypeRepository.findById(id)
                .orElseThrow(() -> new AssetTypeNotFoundException("Asset Type not found with id: " + id));
        assetTypeMapper.updateAssetTypeFromRequest(assetTypeRequest, assetType);

        assetTypeRepository.save(assetType);
        return assetTypeMapper.toAssetTypeResponse(assetType);
    }

    @Override
    @Transactional
    public void deleteAssetType(Long id) {
        AssetType assetType = assetTypeRepository.findById(id)
                .orElseThrow(() -> new AssetTypeNotFoundException("Asset Type not found with id: " + id));

        List<AssetTemp> assets = assetRepository.findAllByAssetType(assetType);
        for(AssetTemp asset: assets) {
            asset.setAssetTypeApi(null);
        }
        assetRepository.saveAll(assets);

        assetTypeRepository.deleteById(id);;
    }

    public List<AssetResponse> findAssetsByAssetTypeId(Long assetTypeId) {
        List<AssetTemp> assets = assetRepository.findByAssetTypeId(assetTypeId);
        return assets.stream().map(assetMapper::toAssetResponse).toList();
    }
}
