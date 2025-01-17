package com.web.backend.application.exception;

public class InvestmentTypeNotFoundException extends RuntimeException {
    public InvestmentTypeNotFoundException(String message) {
        super(message);
    }
}