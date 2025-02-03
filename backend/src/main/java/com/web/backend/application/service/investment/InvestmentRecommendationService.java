package com.web.backend.application.service.investment;

import com.web.backend.application.dto.Recommendation.AssetRecommendation;
import com.web.backend.application.service.alphaVantage.AlphaVantageClient;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.financials.FinancialProfile;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.repository.assetTemp.RAssentTemp;
import com.web.backend.domain.repository.customer.RCustomer;
import com.web.backend.domain.repository.financials.RFinancialProfile;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestmentRecommendationService implements ISInvestmentRecommendationService {

    private final RAssentTemp assetRepository;
    private final AlphaVantageClient alphaVantageClient;
    private final RFinancialProfile financialProfileRepository;
    private final RiskLevelCalculatorService riskLevelCalculatorService;
    private final RCustomer customerRepository;
    private final InvestmentRepository investmentRepository;

    public List<AssetRecommendation> getRecommendations(Long customerId) {
        Optional<FinancialProfile> profile = financialProfileRepository.findByCustomerId(customerId);

        List<AssetTemp> assets = assetRepository.findAll();

        // Calculo para el puntaje y riesgo para cada activo
        return assets.stream()
                .map(asset -> {
                    Map<String, Object> stockData = alphaVantageClient.getStockData(asset.getAssetName());
                    int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
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
        String assetType = asset.getAssetTypeApi();
        return assetType != null && assetType.equalsIgnoreCase("acciones") && mainGoal > 0;
    }


    private double getMaxPotentialReturn() {
        Double maxPotentialReturn = assetRepository.findMaxPotentialReturns();
        return maxPotentialReturn != null ? maxPotentialReturn : 1.0;
    }


    public List<AssetRecommendation> getExternalRecommendations(String keyword, FinancialProfile profile) {
        Map<String, Object> searchResults = alphaVantageClient.searchAssets(keyword);

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
    public void populateAssetsByKeywordFromApi(String keyword, int limit) {
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
                    .tickerSymbol(symbol)
                    .currency(currency)
                    .currentPrice(currentPrice)
                    .potentialReturns(potentialReturns)
                    .sector(sector)
                    .assetTypeApi(assetType)
                    .build();

            int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);
            asset.setRiskLevel(riskLevel);

            assetRepository.save(asset);
        }
    }

    public void populateAssetsByRiskLevel(short riskLevelParam, int limit, List<String> predefinedSymbols) {
        try {

            List<String> matchingSectors = getSectorsByRiskLevel(riskLevelParam);
            List<String> topSymbolsOfTheDay = getTopGainersSymbols();

            if (matchingSectors.isEmpty() && topSymbolsOfTheDay.isEmpty() && (predefinedSymbols == null || predefinedSymbols.isEmpty())) {
                throw new RuntimeException("No hay sectores, símbolos predefinidos o mejores opciones disponibles para procesar.");
            }

            System.out.println("Buscando activos...");

            int processedAssets = 0;

            for (String symbol : topSymbolsOfTheDay) {
                if (processedAssets >= limit) break;
                processedAssets ++;
                processAsset(symbol, riskLevelParam);
            }

            if (predefinedSymbols != null) {
                for (String symbol : predefinedSymbols) {
                    if (processedAssets >= limit) break;
                    processedAssets ++;
                    processAsset(symbol, riskLevelParam);
                }
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getTopGainersSymbols() {
        Map<String, Object> gainersData = alphaVantageClient.getTopGainers();
        if (gainersData == null || !gainersData.containsKey("top_gainers")) {
            System.out.println("No se encontraron las mejores opciones del día.");
            return List.of();
        }

        List<Map<String, Object>> topGainers = (List<Map<String, Object>>) gainersData.get("top_gainers");
        return topGainers.stream()
                .map(entry -> (String) entry.get("ticker"))
                .toList();
    }

    private void processAsset(String symbol, short riskLevelParam) {
        System.out.println("ProcessAsset " + symbol);
        if (assetRepository.existsByTickerSymbol(symbol)) {
            return;
        }
        System.out.println("Loading");
        Map<String, Object> stockData = alphaVantageClient.getStockData(symbol);
        Map<String, Object> overviewData = alphaVantageClient.getOverviewData(symbol);

        if (stockData == null || overviewData == null) {
            System.out.println("Datos incompletos para el símbolo: " + symbol);
            return;
        }

        String name = (String) overviewData.getOrDefault("Name", "Desconocido");
        String currency = (String) overviewData.getOrDefault("Currency", "USD");
        String assetSector = (String) overviewData.getOrDefault("Sector", "Desconocido");
        String assetType = (String) overviewData.getOrDefault("AssetType", "Desconocido");

        if (Objects.equals(name, "Desconocido") || Objects.equals(assetSector, "Desconocido"))
            throw new RuntimeException("No se recibieron datos de la api");

        double currentPrice = extractCurrentPrice(stockData);
        float potentialReturns = calculatePotentialReturns(stockData);

        AssetTemp asset = AssetTemp.builder()
                .assetName(name)
                .tickerSymbol(symbol)
                .currency(currency)
                .currentPrice(currentPrice)
                .potentialReturns(potentialReturns)
                .sector(assetSector)
                .assetTypeApi(assetType)
                .build();

        int riskLevel = riskLevelCalculatorService.calculateRiskLevel(asset, stockData);

        if (riskLevel != riskLevelParam) {
            return;
        }

        asset.setRiskLevel(riskLevel);
        assetRepository.save(asset);

        System.out.println("Activo procesado y guardado: " + asset);
    }


    private List<String> getSectorsByRiskLevel(int riskLevel) {
        return switch (riskLevel) {
            case 1 -> List.of("cdars");
            case 2 -> List.of("etf", "bonos", "fondos mutuos");
            case 3 ->
                    List.of("financial_markets", "economy_fiscal", "economy_monetary", "economy_macro", "manufacturing", "retail_wholesale", "finance");
            case 4 ->
                    List.of("earnings", "mergers_and_acquisitions", "energy_transportation", "life_sciences", "real_estate");
            case 5 -> List.of("blockchain", "ipo", "technology");
            default -> List.of();
        };
    }

    private float calculatePotentialReturns(Map<String, Object> stockData) {
        Map<String, Object> timeSeries = (Map<String, Object>) stockData.get("Time Series (Daily)");
        if (timeSeries == null || timeSeries.size() < 2) return 0.0f;

        List<Double> closingPrices = timeSeries.values().stream()
                .map(entry -> Double.parseDouble(((Map<String, String>) entry).get("4. close")))
                .toList();

        double latestPrice = closingPrices.get(0);
        double previousPrice = closingPrices.get(1);

        return (float) ((latestPrice - previousPrice) / previousPrice);
    }

    public String placeInvestment(Long customerId, String assetId, Double amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));

        AssetTemp asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found with ID: " + assetId));
        System.out.println(asset.getAssetName());
        if (customer.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for this investment.");
        }
        Investment investment = Investment.builder()
                .customer(customer)
                .asset(asset)
                .amount(amount)
                .currentValue(amount)
                .status("ACTIVE")
                .build();
        customer.setBalance(customer.getBalance() - amount);

        investmentRepository.save(investment);
        customerRepository.save(customer);

        return "Investment placed successfully for asset: " + asset.getAssetName();
    }

    public String withdrawCurrentDataInvestment(Long investmentId) {
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new IllegalArgumentException("Investment not found with ID: " + investmentId));

        if (investment.getStatus().equalsIgnoreCase("WITHDRAWN")) {
            throw new IllegalArgumentException("This investment has already been withdrawn.");
        }

        AssetTemp asset = investment.getAsset();
        double currentPrice = asset.getCurrentPrice();

        if (currentPrice <= 0) {
            throw new IllegalStateException("Current asset price is zero or negative. Cannot calculate investment value.");
        }

        System.out.println("Current Asset Price: " + currentPrice);
        System.out.println("Investment Amount: " + investment.getAmount());

        Double currentValue = investment.getAmount() * currentPrice / asset.getCurrentPrice();

        Double profitOrLoss = currentValue - investment.getAmount();

        System.out.println("Calculated Current Value: " + currentValue);
        System.out.println("Profit or Loss: " + profitOrLoss);

        Customer customer = investment.getCustomer();
        if (profitOrLoss < 0) {
            System.out.println("Customer balance before: " + customer.getBalance());
            customer.setBalance(customer.getBalance() + currentValue);
            System.out.println("Customer balance after: " + customer.getBalance());
        } else {
            customer.setBalance(customer.getBalance() + currentValue);
        }

        investment.setCurrentValue(currentValue);
        investment.setStatus("WITHDRAWN");
        investment.setMaturityDate(LocalDateTime.now());

        investmentRepository.save(investment);
        customerRepository.save(customer);

        if (profitOrLoss < 0) {
            return String.format(
                    "Investment withdrawn with a loss of %.2f. Amount credited to balance: %.2f",
                    profitOrLoss, currentValue);
        } else {
            return String.format(
                    "Investment withdrawn with a profit of %.2f. Amount credited to balance: %.2f",
                    profitOrLoss, currentValue);
        }
    }


    public String withdrawInvestment(Long investmentId) {
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new IllegalArgumentException("Investment not found with ID: " + investmentId));

        if (investment.getStatus().equalsIgnoreCase("WITHDRAWN")) {
            throw new IllegalArgumentException("This investment has already been withdrawn.");
        }

        AssetTemp asset = investment.getAsset();
        Map<String, Object> stockData = alphaVantageClient.getStockData(asset.getTickerSymbol());
        double currentPrice = extractCurrentPrice(stockData);

        Double currentValue = (investment.getAmount() * currentPrice / asset.getCurrentPrice());

        Double profitOrLoss = currentValue - investment.getAmount();

        Customer customer = investment.getCustomer();
        customer.setBalance(customer.getBalance() + currentValue);

        investment.setCurrentValue(currentValue);
        investment.setStatus("WITHDRAWN");
        investment.setMaturityDate(LocalDateTime.now());

        investmentRepository.save(investment);
        customerRepository.save(customer);

        return String.format(
                "Investment withdrawn successfully. Profit/Loss: %.2f. Amount credited to balance: %.2f",
                profitOrLoss, currentValue);
    }

}
