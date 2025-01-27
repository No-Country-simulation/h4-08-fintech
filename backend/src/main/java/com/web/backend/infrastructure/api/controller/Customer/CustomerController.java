package com.web.backend.infrastructure.api.controller.Customer;

import com.web.backend.application.DTO.Customer.CustomerUserRequest;
import com.web.backend.application.DTO.User.OnboardingRequest;
import com.web.backend.application.service.User.Customer.CustomerService;
import com.web.backend.application.service.User.Onboarding.OnboardingService;
import com.web.backend.domain.model.Customer.Customer;
import com.web.backend.domain.model.Financials.FinancialProfile;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OnboardingService onboardingService;

    @PostMapping("/user")
    public ResponseEntity<Customer> createCustomerWithUser(@RequestBody CustomerUserRequest customerRequest){

        Customer createCustomer = customerService.createCustomerUser(customerRequest.getEmail(),customerRequest.getCustomer());

        return  ResponseEntity.ok(createCustomer);
    }

    @PostMapping("/onboarding")
    public ResponseEntity<Map<String, Object>> onboardCustomer(
            @RequestParam String email,
            @Valid @RequestBody OnboardingRequest request) {

        Map<String, Object> response = new HashMap<>();
        try {
            FinancialProfile profile = onboardingService.handleOnboarding(email, request);
            response.put("status", "success");
            response.put("financialProfile", profile);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
