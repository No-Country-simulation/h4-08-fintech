package com.web.backend.application.service.interfaces.news;

import com.web.backend.application.dtos.news.NewsRequest;
import com.web.backend.application.dtos.news.NewsResponse;

import java.util.List;

public interface NewsService {
    NewsResponse createNews(NewsRequest request);
    NewsResponse getNewsById(Long id);
    List<NewsResponse> getNewsByDeleted(boolean deleted);
    NewsResponse updateNews(Long id, NewsRequest request);
    void deleteNews(Long id);
}