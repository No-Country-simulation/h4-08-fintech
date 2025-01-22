package com.web.backend.infrastructure.api.controller.asset;

import com.web.backend.application.DTO.AssetResponse;
import com.web.backend.application.service.interfaces.asset.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final String apikey = System.getenv("API_KEY");

    @GetMapping
    public ResponseEntity<List<AssetResponse>> getAssetsByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false") boolean deleted
    ) {
        List<AssetResponse> assetResponses = assetService.getAllAssetsByDeleted(deleted);
        return ResponseEntity.ok(assetResponses);
    }

    @PostMapping("/import/{ticker}")
    public ResponseEntity<AssetResponse> importAsset(@PathVariable String ticker)  {
        AssetResponse assetResponse = assetService.importAsset("GLOBAL_QUOTE", ticker, apikey);
        return new ResponseEntity<>(assetResponse, HttpStatus.CREATED);
    }
}
