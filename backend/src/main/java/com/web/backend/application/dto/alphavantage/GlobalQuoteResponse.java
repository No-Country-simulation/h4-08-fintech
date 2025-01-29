package com.web.backend.application.dto.alphavantage;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GlobalQuoteResponse(
        @JsonProperty("Global Quote") GlobalQuote globalQuote
) {
}
