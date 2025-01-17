package com.web.backend.infrastructure.api.utils.news;

import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.application.dto.news.NewsSourceResponse;
import com.web.backend.domain.model.news.NewsSource;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NewsSourceMapper {

    @Mapping(target = "id", ignore = true)
    NewsSource toNewsSource(NewsSourceRequest newsSourceRequest);

    NewsSourceResponse toNewsSourceResponse(NewsSource newsSource);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNewsSourceFromRequest(NewsSourceRequest newsSourceRequest, @MappingTarget NewsSource newsSource);
}