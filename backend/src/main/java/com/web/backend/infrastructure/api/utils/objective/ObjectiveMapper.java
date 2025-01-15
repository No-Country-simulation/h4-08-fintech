package com.web.backend.infrastructure.api.utils.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;
import com.web.backend.domain.model.objective.Objective;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {
    Objective toObjective (ObjectiveRequest objectiveRequest);
    ObjectiveResponse toObjectiveResponse(Objective objective);
}
