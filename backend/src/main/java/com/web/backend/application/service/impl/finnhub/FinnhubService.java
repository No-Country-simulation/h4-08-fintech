package com.web.backend.application.service.impl.finnhub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.backend.application.dto.asset.PriceUpdateResponse;
import com.web.backend.application.dto.finnhub.Trade;
import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.service.impl.websocket.WebsocketService;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import com.web.backend.domain.repository.assetTemp.RAssentTemp;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FinnhubService {

    private final RAssentTemp assetRepository;
    private static final String TRADE_TYPE = "trade";
    private final ObjectMapper objectMapper;
    private final WebsocketService websocketService;

    // Subscribes to all the assets to the api to receive websocket trade messages
    public void subscribeAssets(Session session) {
        List<AssetTemp> assets = assetRepository.findAll();
        for (AssetTemp asset: assets) {
            String ticker = asset.getTickerSymbol();
            session.getAsyncRemote().sendText("{\"type\":\"subscribe\",\"symbol\":\"" + ticker +"\"}");
        }
    }

    public void processMessage(String message) throws JsonProcessingException {
        Map<String, Object> messageMap = objectMapper.readValue(message, Map.class);
        String type = (String) messageMap.get("type");
        // If the message is a trade, it updates the price and sends the updated price to the sessions
        if (TRADE_TYPE.equals(type)) {
            List<Trade> trades = objectMapper.convertValue(messageMap.get("data"), List.class);
            Trade lastTrade = trades.get(trades.size() - 1);
            updatePrice(lastTrade);
            PriceUpdateResponse response = new PriceUpdateResponse(lastTrade.getS(), lastTrade.getP());
            websocketService.broadcastNewPrices(response);
        }
    }

    // Updates the current price of the asset with the last trade
    private void updatePrice(Trade lastTrade) {
        AssetTemp asset = assetRepository.findById(lastTrade.getS())
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with id: " + lastTrade.getS()));
        asset.setCurrentPrice(lastTrade.getP());
        assetRepository.save(asset);
    }
}