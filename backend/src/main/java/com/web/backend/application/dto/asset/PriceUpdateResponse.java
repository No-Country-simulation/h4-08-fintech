package com.web.backend.application.dto.asset;

public record PriceUpdateResponse(
        String tickerSymbol,
        Double currentPrice
) {
}
