package com.web.backend.infrastructure.api.utils.objective;

import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectiveStatusMapper {
    ObjectiveStatus toObjectiveStatus(ObjectiveStatusRequest objectiveStatusRequest);
    ObjectiveStatusResponse toObjectiveStatusResponse(ObjectiveStatus objectiveStatus);
}
