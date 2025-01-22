package com.web.backend.infrastructure.api.controller.asset;

import com.web.backend.application.DTO.asset.AssetRequest;
import com.web.backend.application.DTO.asset.AssetResponse;
import com.web.backend.application.service.interfaces.asset.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/assets")
@Tag(name = "Assets", description = "Asset management API")
public class AssetController {

    private final AssetService assetService;
    private final String apikey = System.getenv("API_KEY");

    @GetMapping
    @Operation(summary = "Get all assets by deleted status", description = "Retrieves a list of all assets based on their deleted status")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved assets")
    public ResponseEntity<List<AssetResponse>> getAssetsByDeleted(
            @Parameter(description = "Deleted status of assets to retrieve") 
            @RequestParam(name = "deleted", defaultValue = "false") boolean deleted
    ) {
        List<AssetResponse> assetResponses = assetService.getAllAssetsByDeleted(deleted);
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
            @Valid @RequestBody AssetRequest assetRequest
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


    @PostMapping("/import/{ticker}")
    @Operation(summary = "Import an asset", description = "Imports an asset from external API using the provided ticker")
    @ApiResponse(responseCode = "201", description = "Asset successfully imported")
    public ResponseEntity<AssetResponse> importAsset(
            @Parameter(description = "Ticker symbol of the asset to import") 
            @PathVariable String ticker
    ) {
        AssetResponse assetResponse = assetService.importAsset("GLOBAL_QUOTE", ticker, apikey);
        return new ResponseEntity<>(assetResponse, HttpStatus.CREATED);
    }
}
