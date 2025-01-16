package com.web.backend.infrastructure.api.utils.objective;

import com.web.backend.application.dto.news.NewsCategoryRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;
import com.web.backend.domain.model.news.NewsCategory;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ObjectiveStatusMapper {
    ObjectiveStatus toObjectiveStatus(ObjectiveStatusRequest objectiveStatusRequest);

    ObjectiveStatusResponse toObjectiveStatusResponse(ObjectiveStatus objectiveStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateObjectiveStatusFromRequest(ObjectiveStatusRequest objectiveStatusRequest, @MappingTarget ObjectiveStatus objectiveStatus);
}
