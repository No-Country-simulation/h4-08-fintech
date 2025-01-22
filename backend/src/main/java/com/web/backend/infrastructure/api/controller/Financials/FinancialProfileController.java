package com.web.backend.infrastructure.api.controller.Financials;

import com.web.backend.application.service.Financials.FinancialProfileService;
import com.web.backend.domain.model.Financials.FinancialProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/financial-profiles")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<FinancialProfile> createFinancialProfile(
            @PathVariable Long customerId,
            @RequestBody FinancialProfile financialProfile) {

        FinancialProfile createdProfile = financialProfileService.createFinancialProfile(customerId, financialProfile);
        return ResponseEntity.ok(createdProfile);
    }
}