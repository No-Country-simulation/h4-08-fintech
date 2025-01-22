package com.web.backend.infrastructure.api.controller.news;

import com.web.backend.application.DTO.news.NewsCategoryRequest;
import com.web.backend.application.DTO.news.NewsCategoryResponse;
import com.web.backend.application.service.interfaces.news.NewsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news-categories")
@RequiredArgsConstructor
@Tag(name = "News Categories", description = "News Category management API")
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    @PostMapping
    @Operation(summary = "Create a new news category", description = "Creates a new news category based on the provided request")
    @ApiResponse(responseCode = "201", description = "News category created successfully")
    public ResponseEntity<NewsCategoryResponse> createNewsCategory(
            @Valid @RequestBody @Parameter(description = "News category details", required = true) NewsCategoryRequest newsCategory) {
        NewsCategoryResponse createdNewsCategory = newsCategoryService.createNewsCategory(newsCategory);
        return new ResponseEntity<>(createdNewsCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a news category by ID", description = "Retrieves a news category based on the provided ID")
    @ApiResponse(responseCode = "200", description = "News category found")
    @ApiResponse(responseCode = "404", description = "News category not found")
    public ResponseEntity<NewsCategoryResponse> getNewsCategory(
            @PathVariable @Parameter(description = "News category ID", required = true) Long id) {
        NewsCategoryResponse newsCategory = newsCategoryService.getNewsCategoryById(id);
        return ResponseEntity.ok(newsCategory);
    }

    @GetMapping
    @Operation(summary = "Get news categories by deletion status", description = "Retrieves a list of news categories based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of news categories")
    public ResponseEntity<List<NewsCategoryResponse>> getNewsCategoriesByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false")
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        List<NewsCategoryResponse> newsCategories = newsCategoryService.getNewsCategoriesByDeleted(deleted);
        return ResponseEntity.ok(newsCategories);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a news category", description = "Updates an existing news category based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "News category updated successfully")
    @ApiResponse(responseCode = "404", description = "News category not found")
    public ResponseEntity<NewsCategoryResponse> updateNewsCategory(
            @PathVariable @Parameter(description = "News category ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated news category details", required = true) NewsCategoryRequest newsCategory) {
        NewsCategoryResponse updatedNewsCategory = newsCategoryService.updateNewsCategory(id, newsCategory);
        return ResponseEntity.ok(updatedNewsCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a news category", description = "Deletes a news category based on the provided ID")
    @ApiResponse(responseCode = "204", description = "News category deleted successfully")
    @ApiResponse(responseCode = "404", description = "News category not found")
    public ResponseEntity<Void> deleteNewsCategory(
            @PathVariable @Parameter(description = "News category ID", required = true) Long id) {
        newsCategoryService.deleteNewsCategory(id);
        return ResponseEntity.noContent().build();
    }
}