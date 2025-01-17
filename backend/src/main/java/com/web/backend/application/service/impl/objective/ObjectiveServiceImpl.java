package com.web.backend.application.service.impl.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;
import com.web.backend.application.exception.objective.ObjectiveNotFoundException;
import com.web.backend.application.exception.objective.ObjectiveStatusNotFoundException;
import com.web.backend.application.service.interfaces.objective.ObjectiveService;
import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import com.web.backend.domain.repository.objective.ObjectiveRepository;
import com.web.backend.domain.repository.objective.ObjectiveStatusRepository;
import com.web.backend.infrastructure.api.utils.objective.ObjectiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveStatusRepository objectiveStatusRepository;
    private final ObjectiveMapper objectiveMapper;

    @Override
    public ObjectiveResponse createObjective(ObjectiveRequest objectiveRequest) {
        Objective objective = objectiveMapper.toObjective(objectiveRequest);
        ObjectiveStatus status = objectiveStatusRepository.findById(objectiveRequest.objectiveStatusId())
                        .orElseThrow(() -> new ObjectiveStatusNotFoundException("Objective status not found with id: " + objectiveRequest.objectiveStatusId()));
        objective.setObjectiveStatus(status);
        objectiveRepository.save(objective);
        return objectiveMapper.toObjectiveResponse(objective);
    }

    @Override
    public ObjectiveResponse getObjectiveById(Long id) {
        Objective objective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective not found with id: " + id));
        return objectiveMapper.toObjectiveResponse(objective);
    }

    @Override
    public List<ObjectiveResponse> getAllObjectives() {
        List<Objective> objectives = objectiveRepository.findAll();
        return objectives.stream().map(objectiveMapper::toObjectiveResponse).toList();
    }

    @Override
    public ObjectiveResponse updateObjective(Long id, ObjectiveRequest objectiveRequest) {
        Objective existingObjective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective not found with id: " + id));

        objectiveMapper.updateObjectiveFromRequest(objectiveRequest, existingObjective);
        if(objectiveRequest.objectiveStatusId() != null) {
            ObjectiveStatus objectiveStatus = objectiveStatusRepository.findById(objectiveRequest.objectiveStatusId())
                    .orElseThrow(() -> new ObjectiveStatusNotFoundException("Objective status not found with id: " + id));
            existingObjective.setObjectiveStatus(objectiveStatus);
        }

        objectiveRepository.save(existingObjective);
        return objectiveMapper.toObjectiveResponse(existingObjective);
    }

    @Override
    public void deleteObjective(Long id) {
        Objective objective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective not found with id: " + id));
        objective.setDeleted(true);
        objectiveRepository.save(objective);
    }
}
