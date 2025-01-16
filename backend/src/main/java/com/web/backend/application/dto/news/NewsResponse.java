package com.web.backend.application.dto.news;

import com.web.backend.domain.model.news.NewsCategory;
import com.web.backend.domain.model.news.NewsSource;

import java.time.LocalDate;
import java.util.Date;

public record NewsResponse(
        Long id,
        NewsCategory category,
        NewsSource source,
        String author,
        String title,
        String content,
        LocalDate publishDate
) {
}