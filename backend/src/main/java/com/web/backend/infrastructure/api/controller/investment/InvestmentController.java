package com.web.backend.infrastructure.api.controller.Investment;

import com.web.backend.application.DTO.investment.InvestmentRequest;
import com.web.backend.application.DTO.investment.InvestmentResponse;
import com.web.backend.application.service.interfaces.investment.InvestmentService;
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
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
@Tag(name = "Investments", description = "Investment management API")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping
    @Operation(summary = "Create a new investment", description = "Creates a new investment based on the provided request")
    @ApiResponse(responseCode = "201", description = "Investment created successfully")
    public ResponseEntity<InvestmentResponse> createInvestment(
            @Valid @RequestBody @Parameter(description = "Investment details", required = true) InvestmentRequest investment) {
        InvestmentResponse createdInvestment = investmentService.createInvestment(investment);
        return new ResponseEntity<>(createdInvestment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an investment by ID", description = "Retrieves an investment based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Investment found")
    @ApiResponse(responseCode = "404", description = "Investment not found", content = @Content)
    public ResponseEntity<InvestmentResponse> getInvestment(
            @PathVariable @Parameter(description = "Investment ID", required = true) Long id) {
        InvestmentResponse investment = investmentService.getInvestmentById(id);
        return ResponseEntity.ok(investment);
    }

    @GetMapping
    @Operation(summary = "Get investments by deletion status", description = "Retrieves a list of investments based on their deletion status")
    @ApiResponse(responseCode = "200", description = "List of investments")
    public ResponseEntity<List<InvestmentResponse>> getInvestmentsByDeleted(
            @RequestParam(name = "deleted", defaultValue = "false")
            @Parameter(description = "Deletion status (true/false)", required = false) boolean deleted) {
        List<InvestmentResponse> investments = investmentService.getInvestmentsByDeleted(deleted);
        return ResponseEntity.ok(investments);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an investment", description = "Updates an existing investment based on the provided ID and request")
    @ApiResponse(responseCode = "200", description = "Investment updated successfully")
    @ApiResponse(responseCode = "404", description = "Investment not found", content = @Content)
    public ResponseEntity<InvestmentResponse> updateInvestment(
            @PathVariable @Parameter(description = "Investment ID", required = true) Long id,
            @RequestBody @Parameter(description = "Updated investment details", required = true) InvestmentRequest investment) {
        InvestmentResponse updatedInvestment = investmentService.updateInvestment(id, investment);
        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an investment", description = "Deletes an investment based on the provided ID")
    @ApiResponse(responseCode = "204", description = "Investment deleted successfully")
    @ApiResponse(responseCode = "404", description = "Investment not found")
    public ResponseEntity<Void> deleteInvestment(
            @PathVariable @Parameter(description = "Investment ID", required = true) Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}