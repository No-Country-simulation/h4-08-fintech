package com.web.backend.application.service.AlphaVantage;

import com.web.backend.config.AppConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
@AllArgsConstructor
public class AlphaVantageClient {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getTopGainers() {
        String baseUrl = appConfig.getProperty("ALPHAVANTAGE_URL") + "/query";

        String url = UriComponentsBuilder.fromUri(URI.create(baseUrl))
                .queryParam("function", "TOP_GAINERS_LOSERS")
                .queryParam("apikey", "demo")
                .toUriString();

        return restTemplate.getForObject(url, Map.class);
    }

    public Map<String, Object> getStockData(String symbol) {

        String baseUrl = appConfig.getProperty("ALPHAVANTAGE_URL") + "/query";

        String url = UriComponentsBuilder.fromUri(URI.create(baseUrl))
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", symbol)
                .queryParam("apikey", appConfig.getProperty("ALPHA_KEY"))
                .toUriString();
        return (Map<String, Object>) restTemplate.getForObject(url, Map.class);
    }

    public Map<String, Object> getOverviewData(String symbol) {
        String baseUrl = appConfig.getProperty("ALPHAVANTAGE_URL") + "/query";
        System.out.println(symbol);
        String url = UriComponentsBuilder.fromUri(URI.create(baseUrl))
                .queryParam("function", "OVERVIEW")
                .queryParam("symbol", symbol)
                .queryParam("apikey", appConfig.getProperty("ALPHA_KEY"))
                .toUriString();
        return (Map<String, Object>) restTemplate.getForObject(url, Map.class);
    }

    public Map<String, Object> searchAssets(String keyword) {

        String baseUrl = appConfig.getProperty("ALPHAVANTAGE_URL") + "/query";

        System.out.println(baseUrl);

        String url = UriComponentsBuilder.fromUri(URI.create(baseUrl))
                .queryParam("function", "SYMBOL_SEARCH")
                .queryParam("keywords", keyword)
                .queryParam("apikey", appConfig.getProperty("ALPHA_KEY"))
                .toUriString();

        return restTemplate.getForObject(url, Map.class);
    }
}