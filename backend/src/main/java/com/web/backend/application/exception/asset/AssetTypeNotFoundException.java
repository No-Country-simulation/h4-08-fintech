package com.web.backend.application.exception.asset;

public class AssetTypeNotFoundException extends RuntimeException {
    public AssetTypeNotFoundException(String message) {
        super(message);
    }
}