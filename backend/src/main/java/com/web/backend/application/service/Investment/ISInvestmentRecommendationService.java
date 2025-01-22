package com.web.backend.application.service.Investment;

import com.web.backend.application.DTO.Recommendation.AssetRecommendation;
import com.web.backend.domain.model.Financials.FinancialProfile;

import java.util.Collection;
import java.util.List;

public interface ISInvestmentRecommendationService {
    Collection<AssetRecommendation> getRecommendations(Long customeId);
    List<AssetRecommendation> getExternalRecommendations(String keyword, FinancialProfile profile);
}
