package com.web.backend.application.service.Investment;

import com.web.backend.application.DTO.Recommendation.AssetRecommendation;
import com.web.backend.application.service.AlphaVantage.AlphaVantageClient;
import com.web.backend.domain.model.AssetTemp.AssetTemp;
import com.web.backend.domain.model.Financials.FinancialProfile;
import com.web.backend.domain.repository.AssetTemp.RAssentTemp;
import com.web.backend.domain.repository.Financials.RFinancialProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestmentRecommendationService implements ISInvestmentRecommendationService {

    private RAssentTemp assetRepository;
    private AlphaVantageClient alphaVantageClient;
    private RFinancialProfile financialProfileRepository;
    private RiskLevelCalculatorService riskLevelCalculatorService;

    public List<AssetRecommendation> getRecommendations(Long customerId) {
        Optional<FinancialProfile> profile = financialProfileRepository.findByCustomerId(customerId);

        List<AssetTemp> assets = assetRepository.findAll();

        // Calculo para el puntaje y riesgo para cada activo
        return assets.stream()
                .map(asset -> {
                    Map<String, Object> stockData = alphaVantageClient.getStockData(asset.getAssetName());
                    int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
                    asset.setRiskLevel(riskLevel);
                    return calculateRecommendationScore(asset, profile.get());
                })
                .sorted(Comparator.comparingDouble(AssetRecommendation::getScore).reversed())
                .collect(Collectors.toList());
    }

    private AssetRecommendation calculateRecommendationScore(AssetTemp asset, FinancialProfile profile) {
        double riskCompatibility = Math.max(0, 1 - Math.abs(profile.getTolerance() - asset.getRiskLevel()) / 5.0);

        double priceCompatibility = asset.getCurrentPrice() <= profile.getSavingsCapacity()
                ? 1.0
                : profile.getSavingsCapacity() / asset.getCurrentPrice();

        double potentialReturn = asset.getPotentialReturns() / getMaxPotentialReturn();

        double objectiveRelevance = isAssetRelevantToGoal(asset, profile.getMainGoal()) ? 1.0 : 0.5;

        double score = 0.4 * riskCompatibility
                + 0.3 * priceCompatibility
                + 0.2 * potentialReturn
                + 0.1 * objectiveRelevance;

        return new AssetRecommendation(asset, score);
    }

    private boolean isAssetRelevantToGoal(AssetTemp asset, float mainGoal) {
        String assetType = asset.getAssetType();
        return assetType != null && assetType.equalsIgnoreCase("acciones") && mainGoal > 0;
    }


    private double getMaxPotentialReturn() {
        Double maxPotentialReturn = assetRepository.findMaxPotentialReturns();
        return maxPotentialReturn != null ? maxPotentialReturn : 1.0;
    }


    public List<AssetRecommendation> getExternalRecommendations(String keyword, FinancialProfile profile) {
        // Hacer la búsqueda en la API
        Map<String, Object> searchResults = alphaVantageClient.searchAssets(keyword);
        System.out.println("GETEXTERNALLLLLLLLLLLLLLLLLLLLLLLLL");

        // Verificar si la API ha alcanzado el límite de llamadas
        if (searchResults.containsKey("Note")) {
            System.out.println("Límite de llamadas de la API alcanzado: " + searchResults.get("Note"));
        }
        if (searchResults == null || !searchResults.containsKey("bestMatches") || ((List<Map<String, Object>>) searchResults.get("bestMatches")).isEmpty()) {
            System.out.println("No se encontraron activos para el keyword: " + keyword);

            List<AssetTemp> assets = assetRepository.findAll();

            if (assets.isEmpty()) {
                return Collections.emptyList();
            }

            return assets.stream()
                    .map(asset -> {
                        Map<String, Object> stockData = alphaVantageClient.getStockData(asset.getAssetName());
                        int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
                        asset.setRiskLevel(riskLevel);
                        return calculateRecommendationScore(asset, profile);
                    })
                    .sorted(Comparator.comparingDouble(AssetRecommendation::getScore).reversed())
                    .collect(Collectors.toList());
        }

        List<Map<String, Object>> matches = (List<Map<String, Object>>) searchResults.get("bestMatches");

        return matches.stream()
                .map(match -> {
                    String symbol = (String) match.get("1. symbol");
                    Map<String, Object> stockData = alphaVantageClient.getStockData(symbol);

                    AssetTemp asset = AssetTemp.builder()
                            .assetName(symbol)
                            .currency((String) match.get("8. currency"))
                            .currentPrice(extractCurrentPrice(stockData))
                            .build();

                    int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
                    asset.setRiskLevel(riskLevel);

                    return calculateRecommendationScore(asset, profile);
                })
                .sorted(Comparator.comparingDouble(AssetRecommendation::getScore).reversed())
                .collect(Collectors.toList());
    }


    private float extractCurrentPrice(Map<String, Object> stockData) {
        Map<String, Object> timeSeries = (Map<String, Object>) stockData.get("Time Series (Daily)");
        if (timeSeries != null && !timeSeries.isEmpty()) {
            Map.Entry<String, Object> latestEntry = timeSeries.entrySet().iterator().next();
            Map<String, String> dailyData = (Map<String, String>) latestEntry.getValue();
            return Float.parseFloat(dailyData.get("4. close"));
        }
        return 0.0f;
    }

    //    LLENAR LA DB CON DATOS DE LA API
    public void populateAssetsFromApi(String keyword, int limit) {
        Map<String, Object> searchResults = alphaVantageClient.searchAssets(keyword);

        if (searchResults == null || !searchResults.containsKey("bestMatches")) {
            System.out.println("No se encontraron coincidencias para la palabra clave: " + keyword);
            throw new RuntimeException("No se encontraron coincidencias para la palabra clave: " + keyword);
        }

        List<Map<String, Object>> matches = (List<Map<String, Object>>) searchResults.get("bestMatches");

        if (matches == null || matches.isEmpty()) {
            System.out.println("No se encontraron activos para el keyword: " + keyword);
            throw new RuntimeException("No se encontraron activos para el keyword: " + keyword);
        }

        int limitCount = Math.min(matches.size(), limit);

        for (int i = 0; i < limitCount; i++) {
            Map<String, Object> match = matches.get(i);
            String symbol = (String) match.get("1. symbol");
            String name = (String) match.get("2. name");
            String currency = (String) match.get("8. currency");

            Map<String, Object> stockData = alphaVantageClient.getStockData(symbol);

            // OVERVIEW
            Map<String, Object> overviewData = alphaVantageClient.getOverviewData(symbol);
            String sector = (overviewData != null && overviewData.containsKey("Sector"))
                    ? (String) overviewData.get("Sector")
                    : "Desconocido";

            String assetType = (overviewData != null && overviewData.containsKey("AssetType"))
                    ? (String) overviewData.get("AssetType")
                    : "Desconocido";

            double currentPrice = extractCurrentPrice(stockData);
            float potentialReturns = calculatePotentialReturns(stockData);

            AssetTemp asset = AssetTemp.builder()
                    .assetName(name)
                    .tikerSymbol(symbol)
                    .currency(currency)
                    .currentPrice(currentPrice)
                    .potentialReturns(potentialReturns)
                    .sector(sector)
                    .assetType(assetType)
                    .build();

            // Cálculo del nivel de riesgo
            int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
            asset.setRiskLevel(riskLevel);

            // Verificar si el activo esta en la db
            if (!assetRepository.existsByTikerSymbol(symbol)) {
                assetRepository.save(asset);
            }
        }
    }

    private float calculatePotentialReturns(Map<String, Object> stockData) {
        Map<String, Object> timeSeries = (Map<String, Object>) stockData.get("Time Series (Daily)");
        if (timeSeries == null || timeSeries.size() < 2) return 0.0f;

        List<Double> closingPrices = timeSeries.values().stream()
                .map(entry -> Double.parseDouble(((Map<String, String>) entry).get("4. close")))
                .toList();

        double latestPrice = closingPrices.get(0);
        double previousPrice = closingPrices.get(1); // Precio del día anterior.

        return (float) ((latestPrice - previousPrice) / previousPrice);
    }

    private String determineAssetType(String symbol) {
        if (symbol.endsWith(".AX")) return "acciones";
        if (symbol.endsWith(".ETF")) return "ETF";
        return "otros";
    }

}
