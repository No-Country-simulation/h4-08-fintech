package com.web.backend.application.exception.asset;

public class ExternalAPILimit extends RuntimeException {
    public ExternalAPILimit(String message) {
        super(message);
    }
}
