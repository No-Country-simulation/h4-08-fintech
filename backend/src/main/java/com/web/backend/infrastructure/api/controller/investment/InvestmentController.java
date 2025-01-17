package com.web.backend.infrastructure.api.controller.investment;

import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.application.service.interfaces.investment.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<InvestmentResponse> createInvestment(@Valid @RequestBody InvestmentRequest Investment) {
        InvestmentResponse createdInvestment = investmentService.createInvestment(Investment);
        return new ResponseEntity<>(createdInvestment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponse> getInvestment(@PathVariable Long id) {
        InvestmentResponse investment = investmentService.getInvestmentById(id);
        return ResponseEntity.ok(investment);
    }

    @GetMapping
    public ResponseEntity<List<InvestmentResponse>> getAllInvestments() {
        List<InvestmentResponse> investments = investmentService.getAllInvestments();
        return ResponseEntity.ok(investments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentResponse> updateInvestment(@PathVariable Long id, @RequestBody InvestmentRequest Investment) {
        InvestmentResponse updatedInvestment = investmentService.updateInvestment(id, Investment);
        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}