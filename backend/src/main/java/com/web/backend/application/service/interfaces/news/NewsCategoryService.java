package com.web.backend.application.service.interfaces.news;

import com.web.backend.application.dtos.news.NewsCategoryRequest;
import com.web.backend.application.dtos.news.NewsCategoryResponse;

import java.util.List;

public interface NewsCategoryService {
    NewsCategoryResponse createNewsCategory(NewsCategoryRequest request);
    NewsCategoryResponse getNewsCategoryById(Long id);
    List<NewsCategoryResponse> getNewsCategoriesByDeleted(boolean deleted);
    NewsCategoryResponse updateNewsCategory(Long id, NewsCategoryRequest request);
    void deleteNewsCategory(Long id);
}