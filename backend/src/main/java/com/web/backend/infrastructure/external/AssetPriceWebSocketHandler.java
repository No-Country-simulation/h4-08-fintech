package com.web.backend.infrastructure.external;


import com.web.backend.application.service.impl.websocket.WebsocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetPriceWebSocketHandler extends TextWebSocketHandler {

    private final WebsocketService websocketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        websocketService.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        websocketService.removeSession(session);
    }
}