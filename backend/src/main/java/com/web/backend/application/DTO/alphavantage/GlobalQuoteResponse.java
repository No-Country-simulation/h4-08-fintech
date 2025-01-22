package com.web.backend.application.DTO.alphavantage;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GlobalQuoteResponse(
        @JsonProperty("Global Quote") GlobalQuote globalQuote
) {
}
