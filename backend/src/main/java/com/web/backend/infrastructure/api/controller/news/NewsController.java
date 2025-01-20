package com.web.backend.infrastructure.api.controller.news;

import com.web.backend.application.dto.news.NewsRequest;
import com.web.backend.application.dto.news.NewsResponse;
import com.web.backend.application.service.interfaces.news.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "News management API")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    @Operation(summary = "Create a new news", description = "Creates a new news based on the provided request")
    @ApiResponse(responseCode = "201", description = "News created successfully",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    public ResponseEntity<NewsResponse> createNews(
            @Valid @RequestBody @Parameter(description = "News request payload") NewsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.createNews(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get news by ID", description = "Retrieves a news item by its ID")
    @ApiResponse(responseCode = "200", description = "News retrieved successfully",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @ApiResponse(responseCode = "404", description = "News not found", content = @Content)
    public ResponseEntity<NewsResponse> getNews(
            @Parameter(description = "ID of the news to be retrieved") @PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping
    @Operation(summary = "Get news by deleted status", description = "Retrieves a list of news based on their deleted status")
    @ApiResponse(responseCode = "200", description = "News list retrieved successfully",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    public ResponseEntity<List<NewsResponse>> getNewsByDeleted(
            @Parameter(description = "Filter news by deleted status") @RequestParam(name = "deleted", defaultValue = "false") boolean deleted) {
        return ResponseEntity.ok(newsService.getNewsByDeleted(deleted));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update news", description = "Updates an existing news item and returns the updated details")
    @ApiResponse(responseCode = "200", description = "News updated successfully",
            content = @Content(schema = @Schema(implementation = NewsResponse.class)))
    @ApiResponse(responseCode = "404", description = "News not found", content = @Content)
    public ResponseEntity<NewsResponse> updateNews(
            @Parameter(description = "ID of the news to be updated") @PathVariable Long id,
            @RequestBody @Parameter(description = "Updated news request payload") NewsRequest request) {
        return ResponseEntity.ok(newsService.updateNews(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete news", description = "Deletes a news item by its ID")
    @ApiResponse(responseCode = "204", description = "News deleted successfully")
    @ApiResponse(responseCode = "404", description = "News not found", content = @Content)
    public ResponseEntity<Void> deleteNews(
            @Parameter(description = "ID of the news to be deleted") @PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}