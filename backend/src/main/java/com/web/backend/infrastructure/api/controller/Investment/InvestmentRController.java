package com.web.backend.infrastructure.api.controller.Investment;

import com.web.backend.application.DTO.Recommendation.AssetRecommendation;
import com.web.backend.application.service.Investment.InvestmentRecommendationService;
import com.web.backend.domain.model.Financials.FinancialProfile;
import com.web.backend.domain.repository.Financials.RFinancialProfile;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getExternalAssets(
            @PathVariable String keyword,
            @RequestParam Long customerId) {
        try {
            Optional<FinancialProfile> profileOpt = rFinancialProfile.findByCustomerId(customerId);
            if (profileOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Financial profile not found for customer ID: " + customerId);
            }

            List<AssetRecommendation> recommendations = recommendationService.getExternalRecommendations(keyword, profileOpt.get());
            return ResponseEntity.ok(recommendations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populateAssets(@RequestParam String keyword) {
        try {
            recommendationService.populateAssetsFromApi(keyword);
            return ResponseEntity.ok("Assets populated successfully from Alpha Vantage API.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error populating assets: " + e.getMessage());
        }
    }
}
