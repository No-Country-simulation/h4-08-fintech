package com.web.backend.application.service.interfaces.objective;

import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;

import java.util.List;

public interface ObjectiveStatusService {
    ObjectiveStatusResponse createObjectiveStatus(ObjectiveStatusRequest request);
    ObjectiveStatusResponse getObjectiveStatusById(Long id);
    List<ObjectiveStatusResponse> getObjectiveStatusesByDeleted(boolean deleted);
    ObjectiveStatusResponse updateObjectiveStatus(Long id, ObjectiveStatusRequest request);
    void deleteObjectiveStatus(Long id);
}