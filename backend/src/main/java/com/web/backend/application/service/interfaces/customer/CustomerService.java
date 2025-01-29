package com.web.backend.application.service.interfaces.customer;

import com.web.backend.application.DTO.customer.CustomerRequest;
import com.web.backend.application.DTO.customer.CustomerResponse;
import com.web.backend.application.DTO.investment.InvestmentResponse;
import com.web.backend.application.DTO.objective.ObjectiveResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
    void deleteCustomer(Long id);
    List<InvestmentResponse> getInvestmentsByCustomerId(Long customerId, String sortBy, String orderBy);
    List<ObjectiveResponse> getObjectivesByCustomerId(Long customerId, String sortBy, String orderBy);
}