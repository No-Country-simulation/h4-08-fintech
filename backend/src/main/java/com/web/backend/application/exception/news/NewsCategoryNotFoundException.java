package com.web.backend.application.exception.news;

public class NewsCategoryNotFoundException extends RuntimeException {
    public NewsCategoryNotFoundException(String message) {
        super(message);
    }
}
