package com.web.backend.application.exception.news;

public class NewsSourceNotFoundException extends RuntimeException {
    public NewsSourceNotFoundException(String message) {
        super(message);
    }
}
