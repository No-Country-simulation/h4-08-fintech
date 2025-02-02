package com.web.backend.application.service.impl.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.backend.application.dto.asset.PriceUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class WebsocketService {

    private final ObjectMapper objectMapper;
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public void broadcastNewPrices(PriceUpdateResponse response) {
        try {
            String message = objectMapper.writeValueAsString(response);
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
