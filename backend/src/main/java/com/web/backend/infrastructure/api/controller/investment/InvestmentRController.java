package com.web.backend.infrastructure.api.controller.investment;

import com.web.backend.application.dtos.Recommendation.AssetRecommendation;
import com.web.backend.application.service.investment.InvestmentRecommendationService;
import com.web.backend.domain.model.financials.FinancialProfile;
import com.web.backend.domain.repository.financials.RFinancialProfile;
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

    @GetMapping("/recommended")
    public ResponseEntity<?> getRecommendedAssets(
            @RequestParam Long customerId) {
        try {
            Optional<FinancialProfile> profileOpt = rFinancialProfile.findByCustomerId(customerId);
            if (profileOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Financial profile not found for customer ID: " + customerId);
            }

            List<AssetRecommendation> recommendations = recommendationService.getRecommendations(customerId);
            return ResponseEntity.ok(recommendations);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

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
    public ResponseEntity<String> populateAssetsByKeyword(@RequestParam String keyword, @RequestParam Integer limit) {
        try {
            int assetLimit = limit != null ? limit : 10;
            recommendationService.populateAssetsByKeywordFromApi(keyword, assetLimit);
            return ResponseEntity.ok("Assets populated successfully from Alpha Vantage API.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error populating assets: " + e.getMessage());
        }
    }

    @PostMapping("/populate-risk")
    public ResponseEntity<String> populateAssetsByRiskLvl(
            @RequestParam short risklvl,
            @RequestParam Integer limit,
            @RequestBody List<String> predefinedSymbols) {
        try {
            int assetLimit = limit != null ? limit : 10;
            recommendationService.populateAssetsByRiskLevel(risklvl, assetLimit, predefinedSymbols);
            return ResponseEntity.ok("Assets populated successfully from Alpha Vantage API.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error populating assets: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> placeInvestment(
            @RequestParam Long customerId,
            @RequestParam String assetId,
            @RequestParam Double amount) {
        try {
            String result = recommendationService.placeInvestment(customerId, assetId, amount);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawInvestment(
            @RequestParam Long investmentId) {
        try {
            String result = recommendationService.withdrawCurrentDataInvestment(investmentId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

}
