package com.web.backend.application.service.impl.news;

import com.web.backend.application.dto.news.NewsCategoryRequest;
import com.web.backend.application.dto.news.NewsCategoryResponse;
import com.web.backend.application.service.interfaces.news.NewsCategoryService;
import com.web.backend.domain.model.news.NewsCategory;
import com.web.backend.domain.repository.news.NewsCategoryRepository;
import com.web.backend.infrastructure.api.mapper.NewsCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsCategoryMapper newsCategoryMapper;

    @Override
    public NewsCategoryResponse createNewsCategory(NewsCategoryRequest request) {
        NewsCategory category = newsCategoryMapper.toNewsCategory(request);
        category = newsCategoryRepository.save(category);
        return newsCategoryMapper.toNewsCategoryResponse(category);
    }

    @Override
    public NewsCategoryResponse getNewsCategoryById(Long id) {
        NewsCategory category = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsCategory not found"));
        return newsCategoryMapper.toNewsCategoryResponse(category);
    }

    @Override
    public List<NewsCategoryResponse> getAllNewsCategories() {
        return newsCategoryRepository.findAll().stream()
                .map(newsCategoryMapper::toNewsCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NewsCategoryResponse updateNewsCategory(Long id, NewsCategoryRequest request) {
        NewsCategory category = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsCategory not found"));
        newsCategoryMapper.updateNewsCategoryFromRequest(request, category);
        category = newsCategoryRepository.save(category);
        return newsCategoryMapper.toNewsCategoryResponse(category);
    }

    @Override
    public void deleteNewsCategory(Long id) {
        NewsCategory category = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsCategory not found"));
        category.setDeleted(true);
        newsCategoryRepository.save(category);
    }
}