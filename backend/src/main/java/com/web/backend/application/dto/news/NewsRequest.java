package com.web.backend.application.dto.news;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record NewsRequest(
        Long categoryId,
        Long sourceId,
        String author,
        @NotBlank(message = "El titulo no puede estar en blanco")
        String title,
        String content,
        LocalDateTime publishDate
) {
}