package com.web.backend.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.backend.application.service.impl.finnhub.FinnhubService;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@ClientEndpoint
@RequiredArgsConstructor
public class FinnhubWebSocketHandler {

    private final FinnhubService finnhubService;
    private Session session;

    @Value("${finnhub.api.url}")
    private String url;

    @Value("${finnhub.api.key}")
    private String token;

    @PostConstruct
    public void init() {
        connectWebSocket();
    }

    private void connectWebSocket() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = url + "?token=" + token;
        try {
            container.connectToServer(this, URI.create(uri));
        } catch (Exception e) {
            System.err.println("Failed to connect to WebSocket server: " + e.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server");
        this.session = session;
        finnhubService.subscribeAssets(session);
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            finnhubService.processMessage(message);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Session closed: " + closeReason);
    }
}