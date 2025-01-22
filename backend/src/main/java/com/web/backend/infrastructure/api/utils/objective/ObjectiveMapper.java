package com.web.backend.infrastructure.api.utils.objective;

import com.web.backend.application.DTO.objective.ObjectiveRequest;
import com.web.backend.application.DTO.objective.ObjectiveResponse;
import com.web.backend.domain.model.objective.Objective;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {
    Objective toObjective (ObjectiveRequest objectiveRequest);

    ObjectiveResponse toObjectiveResponse(Objective objective);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateObjectiveFromRequest(ObjectiveRequest objectiveRequest, @MappingTarget Objective objective);
}
