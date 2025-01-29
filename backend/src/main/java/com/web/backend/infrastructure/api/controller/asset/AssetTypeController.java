package com.web.backend.infrastructure.api.controller.asset;

import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.application.DTO.asset.AssetTypeRequest;
import com.web.backend.application.DTO.asset.AssetTypeResponse;
import com.web.backend.application.service.interfaces.asset.AssetTypeService;
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
@RequestMapping("/api/v1/asset-types")
@RequiredArgsConstructor
@Tag(name = "Asset Types", description = "Asset Type management API")
public class AssetTypeController {

    private final AssetTypeService assetTypeService;

    @PostMapping
    @Operation(summary = "Create a new Asset type", description = "Creates a new Asset type based on the provided request")
    @ApiResponse(responseCode = "201", description = "Asset type created successfully")
    public ResponseEntity<AssetTypeResponse> createAssetType(
            @Valid @RequestBody @Parameter(description = "Asset type details", required = true) AssetTypeRequest AssetType) {
        AssetTypeResponse createdAssetType = assetTypeService.createAssetType(AssetType);
        return new ResponseEntity<>(createdAssetType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an Asset type by ID", description = "Retrieves an Asset type based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Asset type found")
    @ApiResponse(responseCode = "404", description = "Asset type not found", content = @Content)
    public ResponseEntity<AssetTypeResponse> getAssetType(
            @PathVariable @Parameter(description = "Asset type ID", required = true) Long id) {
        AssetTypeResponse assetType = assetTypeService.getAssetTypeById(id);
        return ResponseEntity.ok(assetType);
    }

    @GetMapping
    @Operation(summary = "Get Asset types by deletion status", description = "Retrieves a list of Asset types based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of Asset types")
    public ResponseEntity<List<AssetTypeResponse>> getAssetTypesByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false")
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        List<AssetTypeResponse> assetTypes = assetTypeService.getAssetTypesByDeleted(deleted);
        return ResponseEntity.ok(assetTypes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Asset type", description = "Updates an existing Asset type based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "Asset type updated successfully")
    @ApiResponse(responseCode = "404", description = "Asset type not found", content = @Content)
    public ResponseEntity<AssetTypeResponse> updateAssetType(
            @PathVariable @Parameter(description = "Asset type ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated Asset type details", required = true) AssetTypeRequest assetType) {
        AssetTypeResponse updatedAssetType = assetTypeService.updateAssetType(id, assetType);
        return ResponseEntity.ok(updatedAssetType);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Asset type", description = "Deletes an Asset type based on the provided ID")
    @ApiResponse(responseCode = "204", description = "Asset type deleted successfully")
    @ApiResponse(responseCode = "404", description = "Asset type not found")
    public ResponseEntity<Void> deleteAssetType(
            @PathVariable @Parameter(description = "Asset type ID", required = true) Long id) {
        assetTypeService.deleteAssetType(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find assets by asset type ID", description = "Returns a list of assets associated with the specified asset type ID")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/{assetTypeId}/assets")
    public ResponseEntity<List<AssetResponse>> findAssetsByAssetTypeId(
            @Parameter(description = "ID of the asset type to find assets for", required = true)
            @PathVariable Long assetTypeId) {
        List<AssetResponse> assets = assetTypeService.findAssetsByAssetTypeId(assetTypeId);
        return ResponseEntity.ok(assets);
    }
}