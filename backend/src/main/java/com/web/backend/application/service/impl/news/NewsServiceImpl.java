package com.web.backend.application.service.impl.news;

import com.web.backend.application.dto.news.NewsRequest;
import com.web.backend.application.dto.news.NewsResponse;
import com.web.backend.application.exception.news.NewsCategoryNotFoundException;
import com.web.backend.application.exception.news.NewsNotFoundException;
import com.web.backend.application.exception.news.NewsSourceNotFoundException;
import com.web.backend.application.service.interfaces.news.NewsService;
import com.web.backend.domain.model.news.News;
import com.web.backend.domain.model.news.NewsCategory;
import com.web.backend.domain.model.news.NewsSource;
import com.web.backend.domain.repository.news.NewsCategoryRepository;
import com.web.backend.domain.repository.news.NewsRepository;
import com.web.backend.domain.repository.news.NewsSourceRepository;
import com.web.backend.infrastructure.api.utils.news.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsSourceRepository newsSourceRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsResponse createNews(NewsRequest request) {
        News news = newsMapper.toNews(request);
        NewsCategory newsCategory = newsCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new NewsCategoryNotFoundException("News category not found with id: " + request.categoryId()));
        news.setCategory(newsCategory);
        NewsSource newsSource = newsSourceRepository.findById(request.sourceId())
                .orElseThrow(() -> new NewsSourceNotFoundException("News source not found with id: " + request.sourceId()));
        news.setSource(newsSource);
        newsRepository.save(news);
        return newsMapper.toNewsResponse(news);
    }

    @Override
    public NewsResponse getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + id));
        return newsMapper.toNewsResponse(news);
    }

    @Override
    public List<NewsResponse> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toNewsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NewsResponse updateNews(Long id, NewsRequest request) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + id));
        if(request.sourceId() != null) {
            NewsCategory newsCategory = newsCategoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NewsCategoryNotFoundException("News category not found with id: " + request.categoryId()));
            news.setCategory(newsCategory);
        }
        if(request.categoryId() != null) {
            NewsSource newsSource = newsSourceRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NewsSourceNotFoundException("News source not found with id: " + request.sourceId()));
            news.setSource(newsSource);
        }
        newsMapper.updateNewsFromRequest(request, news);
        newsRepository.save(news);
        return newsMapper.toNewsResponse(news);
    }

    @Override
    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + id));
        news.setDeleted(true);
        newsRepository.save(news);
    }
}