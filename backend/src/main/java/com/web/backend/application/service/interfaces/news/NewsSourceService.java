package com.web.backend.application.service.interfaces.news;

import com.web.backend.application.DTO.news.NewsSourceRequest;
import com.web.backend.application.DTO.news.NewsSourceResponse;

import java.util.List;

public interface NewsSourceService {
    NewsSourceResponse createNewsSource(NewsSourceRequest request);
    NewsSourceResponse getNewsSourceById(Long id);
    List<NewsSourceResponse> getNewsSourcesByDeleted(boolean deleted);
    NewsSourceResponse updateNewsSource(Long id, NewsSourceRequest request);
    void deleteNewsSource(Long id);
}