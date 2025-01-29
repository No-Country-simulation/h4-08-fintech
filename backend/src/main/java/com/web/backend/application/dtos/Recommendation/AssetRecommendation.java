package com.web.backend.application.dtos.Recommendation;

import com.web.backend.domain.model.assetTemp.AssetTemp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AssetRecommendation {
    private AssetTemp asset;
    private double score;

}
