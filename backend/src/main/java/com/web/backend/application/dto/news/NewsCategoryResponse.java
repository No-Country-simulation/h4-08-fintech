package com.web.backend.application.dto.news;

public record NewsCategoryResponse(
        Long id,
        String name,
        boolean isDeleted
) {
}