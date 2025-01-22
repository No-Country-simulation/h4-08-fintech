package com.web.backend.infrastructure.api.controller.investment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;
import com.web.backend.application.service.interfaces.investment.InvestmentTypeService;
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
@RequestMapping("/api/v1/investment-types")
@RequiredArgsConstructor
@Tag(name = "Investment Types", description = "Investment Type management API")
public class InvestmentTypeController {

    private final InvestmentTypeService investmentTypeService;

    @PostMapping
    @Operation(summary = "Create a new investment type", description = "Creates a new investment type based on the provided request")
    @ApiResponse(responseCode = "201", description = "Investment type created successfully")
    public ResponseEntity<InvestmentTypeResponse> createInvestmentType(
            @Valid @RequestBody @Parameter(description = "Investment type details", required = true) InvestmentTypeRequest investmentType) {
        InvestmentTypeResponse createdInvestmentType = investmentTypeService.createInvestmentType(investmentType);
        return new ResponseEntity<>(createdInvestmentType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an investment type by ID", description = "Retrieves an investment type based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Investment type found")
    @ApiResponse(responseCode = "404", description = "Investment type not found", content = @Content)
    public ResponseEntity<InvestmentTypeResponse> getInvestmentType(
            @PathVariable @Parameter(description = "Investment type ID", required = true) Long id) {
        InvestmentTypeResponse investmentType = investmentTypeService.getInvestmentTypeById(id);
        return ResponseEntity.ok(investmentType);
    }

    @GetMapping
    @Operation(summary = "Get investment types by deletion status", description = "Retrieves a list of investment types based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of investment types")
    public ResponseEntity<List<InvestmentTypeResponse>> getInvestmentTypesByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false")
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        List<InvestmentTypeResponse> investmentTypes = investmentTypeService.getInvestmentTypesByDeleted(deleted);
        return ResponseEntity.ok(investmentTypes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an investment type", description = "Updates an existing investment type based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "Investment type updated successfully")
    @ApiResponse(responseCode = "404", description = "Investment type not found", content = @Content)
    public ResponseEntity<InvestmentTypeResponse> updateInvestmentType(
            @PathVariable @Parameter(description = "Investment type ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated investment type details", required = true) InvestmentTypeRequest investmentType) {
        InvestmentTypeResponse updatedInvestmentType = investmentTypeService.updateInvestmentType(id, investmentType);
        return ResponseEntity.ok(updatedInvestmentType);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an investment type", description = "Deletes an investment type based on the provided ID")
    @ApiResponse(responseCode = "204", description = "Investment type deleted successfully")
    @ApiResponse(responseCode = "404", description = "Investment type not found")
    public ResponseEntity<Void> deleteInvestmentType(
            @PathVariable @Parameter(description = "Investment type ID", required = true) Long id) {
        investmentTypeService.deleteInvestmentType(id);
        return ResponseEntity.noContent().build();
    }
}