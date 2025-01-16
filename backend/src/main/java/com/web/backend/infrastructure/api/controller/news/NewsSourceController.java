package com.web.backend.infrastructure.api.controller.news;

import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.application.dto.news.NewsSourceResponse;
import com.web.backend.application.service.interfaces.news.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news-sources")
@RequiredArgsConstructor
public class NewsSourceController {

    private final NewsSourceService newsSourceService;

    @PostMapping
    public ResponseEntity<NewsSourceResponse> createNewsSource(@RequestBody NewsSourceRequest request) {
        return ResponseEntity.ok(newsSourceService.createNewsSource(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsSourceResponse> getNewsSource(@PathVariable Long id) {
        return ResponseEntity.ok(newsSourceService.getNewsSourceById(id));
    }

    @GetMapping
    public ResponseEntity<List<NewsSourceResponse>> getAllNewsSources() {
        return ResponseEntity.ok(newsSourceService.getAllNewsSources());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsSourceResponse> updateNewsSource(@PathVariable Long id, @RequestBody NewsSourceRequest request) {
        return ResponseEntity.ok(newsSourceService.updateNewsSource(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsSource(@PathVariable Long id) {
        newsSourceService.deleteNewsSource(id);
        return ResponseEntity.noContent().build();
    }
}