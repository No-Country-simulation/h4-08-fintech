package com.web.backend.application.service.interfaces.news;

import com.web.backend.application.dto.news.NewsCategoryRequest;
import com.web.backend.application.dto.news.NewsCategoryResponse;

import java.util.List;

public interface NewsCategoryService {
    NewsCategoryResponse createNewsCategory(NewsCategoryRequest request);
    NewsCategoryResponse getNewsCategoryById(Long id);
    List<NewsCategoryResponse> getAllNewsCategories();
    NewsCategoryResponse updateNewsCategory(Long id, NewsCategoryRequest request);
    void deleteNewsCategory(Long id);
}