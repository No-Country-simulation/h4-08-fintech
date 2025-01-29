package com.web.backend.infrastructure.api.controller.objective;

import com.web.backend.application.dto.objective.ObjectiveStatusRequest;
import com.web.backend.application.dto.objective.ObjectiveStatusResponse;
import com.web.backend.application.service.interfaces.objective.ObjectiveStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/objective-statuses")
@RequiredArgsConstructor
@Tag(name = "Objective Statuses", description = "Objective Statuses management API")
public class ObjectiveStatusController {

    private final ObjectiveStatusService objectiveStatusService;

    @PostMapping
    @Operation(summary = "Create a new objective status", description = "Creates a new objective status based on the provided request")
    @ApiResponse(responseCode = "201", description = "Objective status created successfully")
    public ResponseEntity<ObjectiveStatusResponse> createObjectiveStatus(
            @Valid @RequestBody @Parameter(description = "Objective status details", required = true) ObjectiveStatusRequest request) {
        return ResponseEntity.ok(objectiveStatusService.createObjectiveStatus(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an objective status by ID", description = "Retrieves an objective status based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Objective status found")
    @ApiResponse(responseCode = "404", description = "Objective status not found", content = @Content)
    public ResponseEntity<ObjectiveStatusResponse> getObjectiveStatus(
            @PathVariable @Parameter(description = "Objective status ID", required = true) Long id) {
        return ResponseEntity.ok(objectiveStatusService.getObjectiveStatusById(id));
    }

    @GetMapping
    @Operation(summary = "Get objective statuses by deletion status", description = "Retrieves a list of objective statuses based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of objective statuses")
    public ResponseEntity<List<ObjectiveStatusResponse>> getObjectiveStatusesByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false") 
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        return ResponseEntity.ok(objectiveStatusService.getObjectiveStatusesByDeleted(deleted));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an objective status", description = "Updates an existing objective status based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "Objective status updated successfully")
    @ApiResponse(responseCode = "404", description = "Objective status not found", content = @Content)
    public ResponseEntity<ObjectiveStatusResponse> updateObjectiveStatus(
            @PathVariable @Parameter(description = "Objective status ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated objective status details", required = true) ObjectiveStatusRequest request) {
        return ResponseEntity.ok(objectiveStatusService.updateObjectiveStatus(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an objective status", description = "Deletes an objective status based on the provided ID")
    @ApiResponse(responseCode = "204", description = "Objective status deleted successfully")
    @ApiResponse(responseCode = "404", description = "Objective status not found")
    public ResponseEntity<Void> deleteObjectiveStatus(
            @PathVariable @Parameter(description = "Objective status ID", required = true) Long id) {
        objectiveStatusService.deleteObjectiveStatus(id);
        return ResponseEntity.noContent().build();
    }
}