package com.web.backend.application.service.impl.news;

import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.application.dto.news.NewsSourceResponse;
import com.web.backend.application.exception.news.NewsSourceNotFoundException;
import com.web.backend.application.service.interfaces.news.NewsSourceService;
import com.web.backend.domain.model.news.NewsSource;
import com.web.backend.domain.repository.news.NewsSourceRepository;
import com.web.backend.infrastructure.api.utils.news.NewsSourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsSourceServiceImpl implements NewsSourceService {

    private final NewsSourceRepository newsSourceRepository;
    private final NewsSourceMapper newsSourceMapper;

    @Override
    public NewsSourceResponse createNewsSource(NewsSourceRequest request) {
        NewsSource source = newsSourceMapper.toNewsSource(request);
        newsSourceRepository.save(source);
        return newsSourceMapper.toNewsSourceResponse(source);
    }

    @Override
    public NewsSourceResponse getNewsSourceById(Long id) {
        NewsSource source = newsSourceRepository.findById(id)
                .orElseThrow(() -> new NewsSourceNotFoundException("NewsSource not found with id: " + id));
        return newsSourceMapper.toNewsSourceResponse(source);
    }

    @Override
    public List<NewsSourceResponse> getNewsSourcesByDeleted(boolean deleted) {
        List<NewsSource> newsSources = newsSourceRepository.findAllByIsDeleted(deleted);
        return newsSources.stream().map(newsSourceMapper::toNewsSourceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NewsSourceResponse updateNewsSource(Long id, NewsSourceRequest request) {
        NewsSource source = newsSourceRepository.findById(id)
                .orElseThrow(() -> new NewsSourceNotFoundException("NewsSource not found with id: " + id));
        newsSourceMapper.updateNewsSourceFromRequest(request, source);
        newsSourceRepository.save(source);
        return newsSourceMapper.toNewsSourceResponse(source);
    }

    @Override
    public void deleteNewsSource(Long id) {
        if(!newsSourceRepository.existsById(id))
                throw new NewsSourceNotFoundException("NewsSource not found with id: " + id);
        newsSourceRepository.deleteById(id);
    }
}