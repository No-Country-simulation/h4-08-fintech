package com.web.backend.application.service.interfaces.news;

import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.application.dto.news.NewsSourceResponse;

import java.util.List;

public interface NewsSourceService {
    NewsSourceResponse createNewsSource(NewsSourceRequest request);
    NewsSourceResponse getNewsSourceById(Long id);
    List<NewsSourceResponse> getAllNewsSources();
    NewsSourceResponse updateNewsSource(Long id, NewsSourceRequest request);
    void deleteNewsSource(Long id);
}