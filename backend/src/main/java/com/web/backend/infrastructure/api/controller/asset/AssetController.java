package com.web.backend.infrastructure.api.controller.asset;

import com.web.backend.application.dto.asset.AssetCreateRequest;
import com.web.backend.application.dto.asset.AssetUpdateRequest;
import com.web.backend.application.dto.asset.AssetResponse;
import com.web.backend.application.service.interfaces.asset.AssetService;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/assets")
@Tag(name = "Assets", description = "Asset management API")
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    @Operation(summary = "Get assets", description = "Retrieves a list of assets, optionally filtered by name or ticker or sector and deleted status")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved assets")
    public ResponseEntity<List<AssetResponse>> getAssets(
            @Parameter(description = "Name or ticker to search for")
            @RequestParam(required = false) String keyword,
            @Parameter(description = "Sector to filter assets")
            @RequestParam(required = false) String sector,
            @Parameter(description = "Deleted status of assets to retrieve")
            @RequestParam(name = "deleted", defaultValue = "false") boolean deleted
    ) {
        List<AssetResponse> assetResponses = assetService.getAssets(deleted, keyword, sector);
        return ResponseEntity.ok(assetResponses);
    }

    @GetMapping("/{ticker}")
    @Operation(summary = "Get asset by ticker", description = "Retrieves an asset by its ticker symbol")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved asset")
    @ApiResponse(responseCode = "404", description = "Asset not found", content = @Content)
    public ResponseEntity<AssetResponse> getAssetByTicker(
            @Parameter(description = "Ticker symbol of the asset to retrieve")
            @PathVariable String ticker
    ) {
        AssetResponse assetResponse = assetService.getAssetById(ticker);
        return ResponseEntity.ok(assetResponse);
    }

    @PutMapping("/{ticker}")
    @Operation(summary = "Update an asset", description = "Updates an existing asset with the provided information")
    @ApiResponse(responseCode = "200", description = "Asset successfully updated")
    @ApiResponse(responseCode = "404", description = "Asset not found", content = @Content)
    public ResponseEntity<AssetResponse> updateAsset(
            @Parameter(description = "Ticker symbol of the asset to update")
            @PathVariable String ticker,
            @Parameter(description = "Updated asset information")
            @Valid @RequestBody AssetUpdateRequest assetRequest
    ) {
        AssetResponse updatedAsset = assetService.updateAsset(ticker, assetRequest);
        return ResponseEntity.ok(updatedAsset);
    }

    @DeleteMapping("/{ticker}")
    @Operation(summary = "Delete an asset", description = "Deletes an asset by its ticker symbol")
    @ApiResponse(responseCode = "204", description = "Asset successfully deleted")
    @ApiResponse(responseCode = "404", description = "Asset not found")
    public ResponseEntity<Void> deleteAsset(
            @Parameter(description = "Ticker symbol of the asset to delete")
            @PathVariable String ticker
    ) {
        assetService.deleteAsset(ticker);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create a new asset", description = "Creates a new asset with the provided information")
    @ApiResponse(responseCode = "200", description = "Asset successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<AssetResponse> createAssets(
            @Parameter(description = "Asset information for creation", required = true)
            @Valid @RequestBody AssetCreateRequest assetRequest) {
        AssetResponse createdAsset = assetService.createAsset(assetRequest);
        return ResponseEntity.ok(createdAsset);
    }

    @PostMapping("/file")
    @Operation(summary = "Create multiple assets from CSV", description = "Creates multiple assets from a CSV file")
    @ApiResponse(responseCode = "200", description = "Assets successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid input or file format")
    public ResponseEntity<List<AssetResponse>> createAssetsWithCsv(
            @Parameter(description = "CSV file containing asset information", required = true)
            @RequestPart(name = "assetsFile") MultipartFile file) {
        List<AssetResponse> createdAssets = assetService.createAssetsWithCsv(file);
        return ResponseEntity.ok(createdAssets);
    }
}
