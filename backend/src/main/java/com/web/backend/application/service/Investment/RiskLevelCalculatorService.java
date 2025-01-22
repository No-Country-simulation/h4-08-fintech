package com.web.backend.application.service.Investment;

import com.web.backend.domain.model.AssetTemp.AssetTemp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RiskLevelCalculatorService {

    public int calculateRiskLevel(AssetTemp asset, Map<String, Object> stockData) {
        int riskFromSector = getRiskLevelFromSector(asset.getSector());
        int riskFromReturnsAndPrice = calculateRiskFromReturnsAndPrice(asset.getPotentialReturns(), asset.getCurrentPrice());

        if (stockData == null || stockData.isEmpty()) {
            return (riskFromSector + riskFromReturnsAndPrice) / 2;
        }

        int riskFromVolatility = calculateRiskLevelFromVolatility(stockData);


        return (riskFromVolatility + riskFromSector + riskFromReturnsAndPrice) / 3;
    }

    private int calculateRiskLevelFromVolatility(Map<String, Object> stockData) {
        Map<String, Object> timeSeries = (Map<String, Object>) stockData.get("Time Series (Daily)");
        if (timeSeries == null || timeSeries.isEmpty()) {
            return 3;
        }

        List<Double> closingPrices = timeSeries.values().stream()
                .map(entry -> Double.parseDouble(((Map<String, String>) entry).get("4. close")))
                .toList();

        double average = closingPrices.stream().mapToDouble(val -> val).average().orElse(0.0);
        double standardDeviation = Math.sqrt(closingPrices.stream()
                .mapToDouble(price -> Math.pow(price - average, 2))
                .average()
                .orElse(0.0));

        // Normaliza la desviación estándar (1-5)
        if (standardDeviation < 1) return 1;
        if (standardDeviation < 2) return 2;
        if (standardDeviation < 3) return 3;
        if (standardDeviation < 4) return 4;
        return 5;
    }

    private int getRiskLevelFromSector(String sector) {
        if (sector == null) return 3;
        return switch (sector.toLowerCase()) {
            case "utilities", "consumer staples" -> 1; // Bajo riesgo
            case "technology", "biotech" -> 5; // Alto riesgo
            default -> 3; // Riesgo medio
        };
    }

    private int calculateRiskFromReturnsAndPrice(float potentialReturns, double currentPrice) {
        if (potentialReturns > 0.2 && currentPrice < 10) return 5; // Muy arriesgado
        if (potentialReturns > 0.1) return 4; // Moderadamente arriesgado
        if (potentialReturns > 0.05) return 3; // Riesgo medio
        return 1; // Bajo riesgo
    }
}
