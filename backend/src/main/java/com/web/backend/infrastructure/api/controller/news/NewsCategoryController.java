package com.web.backend.infrastructure.api.controller.news;

import com.web.backend.application.dto.news.NewsCategoryRequest;
import com.web.backend.application.dto.news.NewsCategoryResponse;
import com.web.backend.application.service.interfaces.news.NewsCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news-categories")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    @PostMapping
    public ResponseEntity<NewsCategoryResponse> createNewsCategory(@Valid @RequestBody NewsCategoryRequest request) {
        return ResponseEntity.ok(newsCategoryService.createNewsCategory(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> getNewsCategory(@PathVariable Long id) {
        return ResponseEntity.ok(newsCategoryService.getNewsCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<NewsCategoryResponse>> getAllNewsCategories() {
        return ResponseEntity.ok(newsCategoryService.getAllNewsCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> updateNewsCategory(@PathVariable Long id, @RequestBody NewsCategoryRequest request) {
        return ResponseEntity.ok(newsCategoryService.updateNewsCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsCategory(@PathVariable Long id) {
        newsCategoryService.deleteNewsCategory(id);
        return ResponseEntity.noContent().build();
    }
}