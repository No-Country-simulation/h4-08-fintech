package com.web.backend.infrastructure.api.controller.investment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;
import com.web.backend.application.service.interfaces.investment.InvestmentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investmentTypes")
@RequiredArgsConstructor
public class InvestmentTypeController {

    private final InvestmentTypeService investmentTypeService;

    @PostMapping
    public ResponseEntity<InvestmentTypeResponse> createInvestment(@Valid @RequestBody InvestmentTypeRequest Investment) {
        InvestmentTypeResponse createdInvestmentType = investmentTypeService.createInvestmentType(Investment);
        return new ResponseEntity<>(createdInvestmentType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentTypeResponse> getInvestment(@PathVariable Long id) {
        InvestmentTypeResponse investmentType = investmentTypeService.getInvestmentTypeById(id);
        return ResponseEntity.ok(investmentType);
    }

    @GetMapping
    public ResponseEntity<List<InvestmentTypeResponse>> getAllInvestments(@RequestParam(name = "deleted", defaultValue = "false") boolean deleted) {
        List<InvestmentTypeResponse> investmentTypes = investmentTypeService.getInvestmentTypesByDeleted(deleted);
        return ResponseEntity.ok(investmentTypes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentTypeResponse> updateInvestment(@PathVariable Long id, @RequestBody InvestmentTypeRequest investmentType) {
        InvestmentTypeResponse updatedInvestmentType = investmentTypeService.updateInvestmentType(id, investmentType);
        return ResponseEntity.ok(updatedInvestmentType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestmentType(@PathVariable Long id) {
        investmentTypeService.deleteInvestmentType(id);
        return ResponseEntity.noContent().build();
    }
}