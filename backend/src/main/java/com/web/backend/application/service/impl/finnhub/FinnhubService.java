package com.web.backend.application.service.impl.finnhub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.backend.application.dto.finnhub.Trade;
import com.web.backend.application.exception.asset.AssetNotFoundException;
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
        if (TRADE_TYPE.equals(type)) {
            List<Trade> trades = objectMapper.convertValue(messageMap.get("data"), List.class);
            updatePrice(trades);
        }
    }

    private void updatePrice(List<Trade> trades) {
        Trade lastTrade = trades.get(trades.size() - 1);

        AssetTemp asset = assetRepository.findById(lastTrade.getS())
                .orElseThrow(() -> new AssetNotFoundException("Asset not found with id: " + lastTrade.getS()));
        asset.setCurrentPrice(lastTrade.getP());
        assetRepository.save(asset);
    }
}