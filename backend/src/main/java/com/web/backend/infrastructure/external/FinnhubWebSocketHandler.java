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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final int MAX_RETRY_ATTEMPTS = 5;
    private static final int INITIAL_RETRY_DELAY_SECONDS = 5;

    @PostConstruct
    public void init() {
        connectWebSocket();
    }

    private void connectWebSocket() {
        connectWebSocketWithRetry(0, INITIAL_RETRY_DELAY_SECONDS);
    }

    private void connectWebSocketWithRetry(int retryCount, int delaySeconds) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = url + "?token=" + token;
        try {
            container.connectToServer(this, URI.create(uri));
            logger.info("Successfully connected to WebSocket server");
        } catch (Exception e) {
            logger.error("Failed to connect to WebSocket server (Attempt {})", retryCount + 1, e);
            if (retryCount < MAX_RETRY_ATTEMPTS) {
                int nextDelay = Math.min(delaySeconds * 2, 60); // Cap delay at 60 seconds
                logger.info("Retrying connection in {} seconds...", nextDelay);
                scheduler.schedule(() -> connectWebSocketWithRetry(retryCount + 1, nextDelay), nextDelay, TimeUnit.SECONDS);
            } else {
                logger.error("Max retry attempts reached. Failed to connect to WebSocket server.");
            }
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
        if (closeReason.getCloseCode() == CloseReason.CloseCodes.NORMAL_CLOSURE) {
            logger.info("WebSocket closed normally. Not attempting to reconnect.");
        } else {
            logger.info("Unexpected WebSocket closure. Attempting to reconnect...");
            connectWebSocket();
        }
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