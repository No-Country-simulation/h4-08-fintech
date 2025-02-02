package com.web.backend.infrastructure.api.controller.financials;

import com.web.backend.application.dto.investment.SInvestmentListResponse;
import com.web.backend.application.service.User.customer.CustomerService;
import com.web.backend.application.service.User.financials.FinancialProfileService;
import com.web.backend.application.service.impl.objective.ObjectiveServiceImpl;
import com.web.backend.application.service.interfaces.investment.InvestmentService;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.financials.FinancialProfile;
import com.web.backend.domain.model.objective.Objective;
import com.web.backend.infrastructure.api.utils.auth.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/financial-profiles")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;
    private final CustomerService customerService;
    private final ObjectiveServiceImpl objectiveService;
    private final InvestmentService investmentService;
    private final AESUtil aesUtil;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<FinancialProfile> createFinancialProfile(
            @PathVariable Long customerId,
            @RequestBody FinancialProfile financialProfile) {

        FinancialProfile createdProfile = financialProfileService.createFinancialProfile(customerId, financialProfile);
        return ResponseEntity.ok(createdProfile);
    }


    @GetMapping("/dashboard")
    public ResponseEntity<?> getProfileDataDashboard(
            @CookieValue(value = "customer_id", required = false) String customerId,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        try {
            Long decryptedCustomerId = customerId != null ? Long.valueOf(aesUtil.decrypt(customerId)) : null;
            Customer customer = customerService.getById(decryptedCustomerId);
            FinancialProfile financialProfile = financialProfileService.getFinancialProfile(customer.getId());

            Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(page, limit, sort);

            Page<Objective> objectivesPage = objectiveService.getAllCustomerObjLimit(customer.getId(), pageable);

            assert customerId != null;

            SInvestmentListResponse investmentListResponses = investmentService.getAllInvestmentCustomer(
                    Long.valueOf(customerId),
                    limit,
                    page,
                    sortBy,
                    ascending
            );

            // Mapeo para el cliente
            Map<String, Object> response = new HashMap<>();
            response.put("name", customer.getFullName());
            response.put("balance", customer.getBalance());
            response.put("transactions", investmentListResponses.totalTransactions);
            response.put("save", financialProfile.getSavingsCapacity());
            response.put("incomes", financialProfile.getEstimatedIncome());
            response.put("expensives", financialProfile.getEstimatedExpenses());

            // Mapeo de objetivos
            List<Map<String, Object>> mappedObjectives = objectivesPage.getContent().stream().map(obj -> {
                Map<String, Object> objectiveData = new HashMap<>();
                objectiveData.put("name", obj.getTitle());
                objectiveData.put("dueDate", obj.getDueDate());  // Convertir a formato adecuado
                objectiveData.put("savePerMonth", obj.getProgress());  // O calcular como prefieras
                objectiveData.put("goal", obj.getTargetAmount());
                objectiveData.put("current", obj.getCurrentAmount());
                return objectiveData;
            }).collect(Collectors.toList());

            response.put("objetives", mappedObjectives);
            response.put("totalPages", objectivesPage.getTotalPages());
            response.put("totalElements", objectivesPage.getTotalElements());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error obteniendo los datos del dashboard: " + e.getMessage()));
        }
    }

}