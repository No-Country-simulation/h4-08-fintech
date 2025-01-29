package com.web.backend.infrastructure.api.controller.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;
import com.web.backend.application.service.interfaces.objective.ObjectiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/objectives")
@RequiredArgsConstructor
@Tag(name = "Objectives", description = "Objectives management API")
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    @PostMapping
    @Operation(summary = "Create a new objective", description = "Creates a new objective based on the provided request")
    @ApiResponse(responseCode = "201", description = "Objective created successfully")
    public ResponseEntity<ObjectiveResponse> createObjective(
            @Valid @RequestBody @Parameter(description = "Objective details", required = true) ObjectiveRequest objectiveRequest) {
        ObjectiveResponse createdObjective = objectiveService.createObjective(objectiveRequest);
        return new ResponseEntity<>(createdObjective, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an objective by ID", description = "Retrieves an objective based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Objective found")
    @ApiResponse(responseCode = "404", description = "Objective not found", content = @Content)
    public ResponseEntity<ObjectiveResponse> getObjective(
            @PathVariable @Parameter(description = "Objective ID", required = true) Long id) {
        ObjectiveResponse objective = objectiveService.getObjectiveById(id);
        return ResponseEntity.ok(objective);
    }

    @GetMapping
    @Operation(summary = "Get objectives by deletion status", description = "Retrieves a list of objectives based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of objectives")
    public ResponseEntity<List<ObjectiveResponse>> getObjectivesByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false") 
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        List<ObjectiveResponse> objectives = objectiveService.getObjectivesByDeleted(deleted);
        return ResponseEntity.ok(objectives);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an objective", description = "Updates an existing objective based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "Objective updated successfully")
    @ApiResponse(responseCode = "404", description = "Objective not found", content = @Content)
    public ResponseEntity<ObjectiveResponse> updateObjective(
            @PathVariable @Parameter(description = "Objective ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated objective details", required = true) ObjectiveRequest objectiveRequest) {
        ObjectiveResponse updatedObjective = objectiveService.updateObjective(id, objectiveRequest);
        return ResponseEntity.ok(updatedObjective);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an objective", description = "Deletes an objective based on the provided ID")
    @ApiResponse(responseCode = "204", description = "Objective deleted successfully")
    @ApiResponse(responseCode = "404", description = "Objective not found")
    public ResponseEntity<Void> deleteObjective(
            @PathVariable @Parameter(description = "Objective ID", required = true) Long id) {
        objectiveService.deleteObjective(id);
        return ResponseEntity.noContent().build();
    }
}
