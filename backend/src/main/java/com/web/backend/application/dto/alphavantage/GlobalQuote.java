package com.web.backend.application.dto.alphavantage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record GlobalQuote(
        @JsonProperty("01. symbol") String symbol,
        @JsonProperty("02. open") Float open,
        @JsonProperty("03. high") Float high,
        @JsonProperty("04. low") Float low,
        @JsonProperty("05. price") Float price,
        @JsonProperty("06. volume") Long volume,
        @JsonProperty("07. latest trading day") LocalDate latestTradingDay,
        @JsonProperty("08. previous close") Float previousClose,
        @JsonProperty("09. change") Float change,
        @JsonProperty("10. change percent") String changePercent
) {}