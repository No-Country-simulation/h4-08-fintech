package com.web.backend.infrastructure.api.controller.customer;

import com.web.backend.application.DTO.customer.CustomerRequest;
import com.web.backend.application.DTO.customer.CustomerResponse;
import com.web.backend.application.DTO.investment.InvestmentResponse;
import com.web.backend.application.DTO.objective.ObjectiveResponse;
import com.web.backend.application.service.interfaces.customer.CustomerService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "Customer management API")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided information")
    @ApiResponse(responseCode = "201", description = "Customer successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Parameter(description = "Customer information for creation", required = true)
            @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse createdCustomer = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by ID", description = "Retrieves a customer by their ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved customer")
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<CustomerResponse> getCustomerById(
            @Parameter(description = "ID of the customer to retrieve")
            @PathVariable Long id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer", description = "Updates an existing customer with the provided information")
    @ApiResponse(responseCode = "200", description = "Customer successfully updated")
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<CustomerResponse> updateCustomer(
            @Parameter(description = "ID of the customer to update")
            @PathVariable Long id,
            @Parameter(description = "Updated customer information")
            @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer by their ID")
    @ApiResponse(responseCode = "204", description = "Customer successfully deleted")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID of the customer to delete")
            @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/investments")
    @Operation(summary = "Get investments by customer", description = "Retrieves a list of investments for a specific customer")
    public ResponseEntity<List<InvestmentResponse>> getInvestmentsByCustomer(
            @Parameter(description = "Customer ID") @PathVariable Long id,
            @Parameter(description = "Sort by field (default: createdAt)") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort order (asc or desc, default: desc)") @RequestParam(defaultValue = "desc") String orderBy) {
        List<InvestmentResponse> investments = customerService.getInvestmentsByCustomerId(id, sortBy, orderBy);
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/{id}/objectives")
    @Operation(summary = "Get objectives by customer", description = "Retrieves a list of objectives for a specific customer")
    public ResponseEntity<List<ObjectiveResponse>> getObjectivesByCustomer(
            @Parameter(description = "Customer ID") @PathVariable Long id,
            @Parameter(description = "Sort by field (default: createdAt)") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort order (asc or desc, default: desc)") @RequestParam(defaultValue = "desc") String orderBy) {
        List<ObjectiveResponse> objectives = customerService.getObjectivesByCustomerId(id, sortBy, orderBy);
        return ResponseEntity.ok(objectives);
    }
}