package com.web.backend.infrastructure.api.controller.objective;

import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;
import com.web.backend.application.service.interfaces.objective.ObjectiveStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/objective-statuses")
@RequiredArgsConstructor
public class ObjectiveStatusController {

    private final ObjectiveStatusService objectiveStatusService;

    @PostMapping
    public ResponseEntity<ObjectiveStatusResponse> createObjectiveStatus(@Valid @RequestBody ObjectiveStatusRequest request) {
        return ResponseEntity.ok(objectiveStatusService.createObjectiveStatus(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveStatusResponse> getObjectiveStatus(@PathVariable Long id) {
        return ResponseEntity.ok(objectiveStatusService.getObjectiveStatusById(id));
    }

    @GetMapping
    public ResponseEntity<List<ObjectiveStatusResponse>> getAllObjectiveStatuses() {
        return ResponseEntity.ok(objectiveStatusService.getAllObjectiveStatuses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveStatusResponse> updateObjectiveStatus(@PathVariable Long id, @RequestBody ObjectiveStatusRequest request) {
        return ResponseEntity.ok(objectiveStatusService.updateObjectiveStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjectiveStatus(@PathVariable Long id) {
        objectiveStatusService.deleteObjectiveStatus(id);
        return ResponseEntity.noContent().build();
    }
}