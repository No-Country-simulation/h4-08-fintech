package com.web.backend.application.exception.investment;

public class InvestmentNotFoundException extends RuntimeException {
    public InvestmentNotFoundException(String message) {
        super(message);
    }
}