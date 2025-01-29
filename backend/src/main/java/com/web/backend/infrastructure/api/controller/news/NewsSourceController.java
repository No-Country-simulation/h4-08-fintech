package com.web.backend.infrastructure.api.controller.news;
import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.application.dto.news.NewsSourceResponse;
import com.web.backend.application.service.interfaces.news.NewsSourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news-sources")
@RequiredArgsConstructor
@Tag(name = "News Sources", description = "API for managing news sources")
public class NewsSourceController {

    private final NewsSourceService newsSourceService;

    @PostMapping
    @Operation(summary = "Create a new news source", description = "Creates a new news source based on the provided request")
    @ApiResponse(responseCode = "201", description = "News source created successfully")
    public ResponseEntity<NewsSourceResponse> createNewsSource(
            @Valid @RequestBody @Parameter(description = "News source request payload") NewsSourceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsSourceService.createNewsSource(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get news source by ID", description = "Retrieves a news source by its ID")
    @ApiResponse(responseCode = "200", description = "News source retrieved successfully")
    @ApiResponse(responseCode = "404", description = "News source not found", content = @Content)
    public ResponseEntity<NewsSourceResponse> getNewsSource(
            @Parameter(description = "ID of the news source to be retrieved") @PathVariable Long id) {
        return ResponseEntity.ok(newsSourceService.getNewsSourceById(id));
    }

    @GetMapping
    @Operation(summary = "Get news sources by deleted status", description = "Retrieves a list of news sources based on their deleted status")
    @ApiResponse(responseCode = "200", description = "News sources list retrieved successfully")
    public ResponseEntity<List<NewsSourceResponse>> getNewsSourcesByDeleted(
            @Parameter(description = "Filter news sources by deleted status") @RequestParam boolean deleted) {
        return ResponseEntity.ok(newsSourceService.getNewsSourcesByDeleted(deleted));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update news source", description = "Updates an existing news source and returns the updated details")
    @ApiResponse(responseCode = "200", description = "News source updated successfully")
    @ApiResponse(responseCode = "404", description = "News source not found", content = @Content)
    public ResponseEntity<NewsSourceResponse> updateNewsSource(
            @Parameter(description = "ID of the news source to be updated") @PathVariable Long id,
            @RequestBody @Parameter(description = "Updated news source request payload") NewsSourceRequest request) {
        return ResponseEntity.ok(newsSourceService.updateNewsSource(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete news source", description = "Deletes a news source by its ID")
    @ApiResponse(responseCode = "204", description = "News source deleted successfully")
    @ApiResponse(responseCode = "404", description = "News source not found")
    public ResponseEntity<Void> deleteNewsSource(
            @Parameter(description = "ID of the news source to be deleted") @PathVariable Long id) {
        newsSourceService.deleteNewsSource(id);
        return ResponseEntity.noContent().build();
    }
}