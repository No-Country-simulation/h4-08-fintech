package com.web.backend.application.dto.news;

import java.time.LocalDate;

public record NewsRequest(
        Long categoryId,
        Long sourceId,
        String author,
        String title,
        String content,
        LocalDate publishDate
) {
}