package com.web.backend.application.service.impl.objective;

import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;
import com.web.backend.application.exception.objective.ObjectiveStatusNotFoundException;
import com.web.backend.application.service.interfaces.objective.ObjectiveStatusService;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import com.web.backend.domain.repository.objective.ObjectiveStatusRepository;
import com.web.backend.infrastructure.api.utils.objective.ObjectiveStatusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectiveStatusServiceImpl implements ObjectiveStatusService {

    private final ObjectiveStatusRepository objectiveStatusRepository;
    private final ObjectiveStatusMapper objectiveStatusMapper;

    @Override
    public ObjectiveStatusResponse createObjectiveStatus(@Valid ObjectiveStatusRequest request) {
        ObjectiveStatus status = objectiveStatusMapper.toObjectiveStatus(request);
        objectiveStatusRepository.save(status);
        return objectiveStatusMapper.toObjectiveStatusResponse(status);
    }

    @Override
    public ObjectiveStatusResponse getObjectiveStatusById(Long id) {
        ObjectiveStatus status = objectiveStatusRepository.findById(id)
                .orElseThrow(() -> new ObjectiveStatusNotFoundException("ObjectiveStatus not found with id: " + id));
        return objectiveStatusMapper.toObjectiveStatusResponse(status);
    }

    @Override
    public List<ObjectiveStatusResponse> getAllObjectiveStatuses() {
        return objectiveStatusRepository.findAll().stream()
                .map(objectiveStatusMapper::toObjectiveStatusResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ObjectiveStatusResponse updateObjectiveStatus(Long id, ObjectiveStatusRequest request) {
        ObjectiveStatus status = objectiveStatusRepository.findById(id)
                .orElseThrow(() -> new ObjectiveStatusNotFoundException("ObjectiveStatus not found with id: " + id));
        objectiveStatusMapper.updateObjectiveStatusFromRequest(request, status);
        objectiveStatusRepository.save(status);
        return objectiveStatusMapper.toObjectiveStatusResponse(status);
    }

    @Override
    public void deleteObjectiveStatus(Long id) {
        ObjectiveStatus status = objectiveStatusRepository.findById(id)
                .orElseThrow(() -> new ObjectiveStatusNotFoundException("ObjectiveStatus not found with id: " + id));
        status.setDeleted(true);
        objectiveStatusRepository.save(status);
    }
}