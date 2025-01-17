package com.web.backend.infrastructure.api.controller.news;

import com.web.backend.application.dto.news.NewsRequest;
import com.web.backend.application.dto.news.NewsResponse;
import com.web.backend.application.service.interfaces.news.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@Valid @RequestBody NewsRequest request) {
        return ResponseEntity.ok(newsService.createNews(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getNewsByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false") boolean deleted
    ) {
        return ResponseEntity.ok(newsService.getNewsByDeleted(deleted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@PathVariable Long id, @RequestBody NewsRequest request) {
        return ResponseEntity.ok(newsService.updateNews(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}