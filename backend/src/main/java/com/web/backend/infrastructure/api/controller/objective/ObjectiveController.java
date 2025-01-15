package com.web.backend.infrastructure.api.controller.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;
import com.web.backend.application.service.interfaces.objective.ObjectiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/objectives")
@RequiredArgsConstructor
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    @PostMapping
    public ResponseEntity<ObjectiveResponse> createObjective(@RequestBody ObjectiveRequest objectiveRequest) {
        ObjectiveResponse createdObjective = objectiveService.createObjective(objectiveRequest);
        return new ResponseEntity<>(createdObjective, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveResponse> getObjective(@PathVariable Long id) {
        ObjectiveResponse objective = objectiveService.getObjectiveById(id);
        return ResponseEntity.ok(objective);
    }

    @GetMapping
    public ResponseEntity<List<ObjectiveResponse>> getALlObjectives() {
        List<ObjectiveResponse> objectives = objectiveService.getAllObjectives();
        return ResponseEntity.ok(objectives);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveResponse> updateObjective(@PathVariable Long id, @RequestBody ObjectiveRequest objectiveRequest) {
        ObjectiveResponse updatedObjective = objectiveService.updateObjective(id, objectiveRequest);
        return ResponseEntity.ok(updatedObjective);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjective(@PathVariable Long id) {
        objectiveService.deleteObjective(id);
        return ResponseEntity.noContent().build();
    }
}
