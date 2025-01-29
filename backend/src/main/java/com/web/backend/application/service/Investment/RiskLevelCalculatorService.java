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

        // Si no hay datos históricos, calcula solo con los factores disponibles
        if (stockData == null || stockData.isEmpty()) {
            return (riskFromSector + riskFromReturnsAndPrice) / 2;
        }

        int riskFromVolatility = calculateRiskLevelFromVolatility(stockData);
        int riskFromAssetType = getRiskLevelFromAssetType(asset.getAssetType());

        return (riskFromVolatility + riskFromSector + riskFromReturnsAndPrice + riskFromAssetType) / 4;
    }

    /**
     * Calcula el riesgo basado en la volatilidad de los precios.
     */
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
        if (standardDeviation < 1) return 1; // Baja volatilidad
        if (standardDeviation < 2) return 2;
        if (standardDeviation < 3) return 3;
        if (standardDeviation < 4) return 4;
        return 5; // Alta volatilidad
    }

    /**
     * Devuelve el nivel de riesgo según el sector del activo.
     */
    private int getRiskLevelFromSector(String sector) {
        if (sector == null) return 3;
        return switch (sector.toLowerCase()) {
            case "blockchain" -> 5; // Alto riesgo
            case "earnings" -> 4; // Riesgo alto-moderado
            case "ipo" -> 5; // Alto riesgo
            case "mergers_and_acquisitions" -> 4; // Riesgo alto-moderado
            case "financial_markets" -> 3; // Riesgo medio
            case "economy_fiscal", "economy_monetary", "economy_macro" -> 3; // Riesgo medio
            case "energy_transportation" -> 4; // Riesgo alto-moderado
            case "finance" -> 3; // Riesgo medio
            case "life_sciences" -> 4; // Riesgo alto-moderado
            case "manufacturing" -> 3; // Riesgo medio
            case "real_estate" -> 4; // Riesgo alto-moderado
            case "retail_wholesale" -> 3; // Riesgo medio
            case "technology" -> 5; // Alto riesgo
            default -> 3; // Riesgo medio
        };
    }
    /**
     * Calcula el nivel de riesgo basado en los retornos potenciales y el precio actual.
     */
    private int calculateRiskFromReturnsAndPrice(float potentialReturns, double currentPrice) {
        if (potentialReturns > 0.25 && currentPrice < 5) return 5; // Muy arriesgado
        if (potentialReturns > 0.15) return 4; // Moderadamente arriesgado
        if (potentialReturns > 0.05) return 3; // Riesgo medio
        return 1; // Bajo riesgo
    }

    /**
     * Devuelve el nivel de riesgo según el tipo de activo.
     */
    private int getRiskLevelFromAssetType(String assetType) {
        if (assetType == null) return 3;
        return switch (assetType.toLowerCase()) {
            case "acciones" -> 4; // Riesgo moderado-alto
            case "etf" -> 2; // Riesgo bajo-moderado
            case "cdars" -> 1; // Muy bajo riesgo
            case "bonos", "fondos mutuos" -> 2; // Bajo riesgo
            default -> 3; // Riesgo medio
        };
    }
}
