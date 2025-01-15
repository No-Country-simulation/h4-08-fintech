package com.web.backend.application.service.interfaces.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;

import java.util.List;

public interface ObjectiveService {
    ObjectiveResponse createObjective(ObjectiveRequest objectiveRequest);

    ObjectiveResponse getObjectiveById(Long id);

    List<ObjectiveResponse> getAllObjectives();

    ObjectiveResponse updateObjective(Long id, ObjectiveRequest objectiveRequest);

    void deleteObjective(Long id);
}
