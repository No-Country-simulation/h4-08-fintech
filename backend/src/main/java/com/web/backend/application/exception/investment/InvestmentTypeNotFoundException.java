package com.web.backend.application.exception.investment;

public class InvestmentTypeNotFoundException extends RuntimeException {
    public InvestmentTypeNotFoundException(String message) {
        super(message);
    }
}