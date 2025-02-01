package com.web.backend.infrastructure.external;

import com.web.backend.application.dto.alphavantage.GlobalQuoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "alphavantage",
        url = "https://www.alphavantage.co/query")
public interface AlphaVantageClient {

    @GetMapping
    GlobalQuoteResponse getGlobalQuote(@RequestParam("function") String function,
                                       @RequestParam("symbol") String symbol,
                                       @RequestParam("apikey") String apiKey);
}
