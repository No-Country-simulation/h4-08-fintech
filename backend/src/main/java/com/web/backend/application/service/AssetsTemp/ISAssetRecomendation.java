package com.web.backend.application.service.AssetsTemp;

import com.web.backend.domain.model.AssetTemp.AssetTemp;
import com.web.backend.domain.model.investment.Investment;

import java.util.Collection;

public interface ISAssetRecomendation {
    Collection<AssetTemp> getRecomendedAssets();
    Collection<AssetTemp> getAllAssetsById();
    Collection<AssetTemp> getExampleAssets();
}
