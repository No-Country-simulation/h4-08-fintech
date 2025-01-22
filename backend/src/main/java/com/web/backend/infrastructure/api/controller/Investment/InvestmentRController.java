package com.web.backend.infrastructure.api.controller.Investment;

import com.web.backend.application.DTO.Recommendation.AssetRecommendation;
import com.web.backend.application.service.Investment.InvestmentRecommendationService;
import com.web.backend.domain.model.Financials.FinancialProfile;
import com.web.backend.domain.repository.Financials.RFinancialProfile;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investment")
@AllArgsConstructor
public class InvestmentRController {

    private final InvestmentRecommendationService recommendationService;
    private final RFinancialProfile rFinancialProfile;

    @GetMapping("/external/{keyword}")
    public ResponseEntity<List<AssetRecommendation>> getExternalAssets(
            @PathVariable String keyword,
            @RequestParam Long customerId) {
        Optional<FinancialProfile> profile = rFinancialProfile.findByCustomerId(customerId);
        List<AssetRecommendation> recommendations = recommendationService.getExternalRecommendations(keyword, profile.orElse(null));
        return ResponseEntity.ok(recommendations);
    }
}
