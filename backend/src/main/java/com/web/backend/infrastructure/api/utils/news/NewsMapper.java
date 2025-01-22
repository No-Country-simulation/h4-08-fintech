package com.web.backend.infrastructure.api.utils.news;

import com.web.backend.application.DTO.news.NewsRequest;
import com.web.backend.application.DTO.news.NewsResponse;
import com.web.backend.domain.model.news.News;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {NewsCategoryMapper.class, NewsSourceMapper.class})
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    News toNews(NewsRequest newsRequest);

    NewsResponse toNewsResponse(News news);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNewsFromRequest(NewsRequest newsRequest, @MappingTarget News news);
}