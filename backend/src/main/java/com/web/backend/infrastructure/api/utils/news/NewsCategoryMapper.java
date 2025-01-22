package com.web.backend.infrastructure.api.utils.news;

import com.web.backend.application.dto.news.NewsCategoryRequest;
import com.web.backend.application.dto.news.NewsCategoryResponse;
import com.web.backend.domain.model.news.NewsCategory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NewsCategoryMapper {

    @Mapping(target = "id", ignore = true)
    NewsCategory toNewsCategory(NewsCategoryRequest newsCategoryRequest);

    NewsCategoryResponse toNewsCategoryResponse(NewsCategory newsCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNewsCategoryFromRequest(NewsCategoryRequest newsCategoryRequest, @MappingTarget NewsCategory newsCategory);
}