package com.web.backend.application.dto.news;

import java.time.LocalDateTime;

public record NewsResponse(
        Long id,
        NewsCategoryResponse category,
        NewsSourceResponse source,
        String author,
        String title,
        String content,
        LocalDateTime publishDate
) {
}