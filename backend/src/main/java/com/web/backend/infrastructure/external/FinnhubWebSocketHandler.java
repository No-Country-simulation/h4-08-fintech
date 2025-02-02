package com.web.backend.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.backend.application.service.impl.finnhub.FinnhubService;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
@ClientEndpoint
@RequiredArgsConstructor
public class FinnhubWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(FinnhubWebSocketHandler.class);

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
        } catch (DeploymentException | IOException e) {
            logger.error("Failed to connect to WebSocket server", e);
            throw new RuntimeException("Failed to connect to WebSocket server", e);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected to server");
        this.session = session;
        try {
            finnhubService.subscribeAssets(session);
        } catch (Exception e) {
            logger.error("Failed to subscribe to assets", e);
            closeSession(session, "Failed to subscribe to assets");
        }
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            finnhubService.processMessage(message);
        } catch (JsonProcessingException e) {
            logger.error("Failed to process message", e);
        } catch (Exception e) {
            logger.error("Unexpected error while processing message", e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session closed: {}", closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("WebSocket error", throwable);
        closeSession(session, "WebSocket error occurred");
    }

    private void closeSession(Session session, String reason) {
        try {
            session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, reason));
        } catch (IOException e) {
            logger.error("Failed to close WebSocket session", e);
        }
    }
}