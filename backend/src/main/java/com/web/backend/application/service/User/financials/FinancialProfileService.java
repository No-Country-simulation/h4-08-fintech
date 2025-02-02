package com.web.backend.application.service.User.financials;

import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.financials.FinancialProfile;
import com.web.backend.domain.repository.customer.RCustomer;
import com.web.backend.domain.repository.financials.RFinancialProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialProfileService {

    private final RFinancialProfile financialProfileRepository;
    private final RCustomer customerRepository;

    public FinancialProfile getFinancialProfile(Long customerId){
        try{

            return financialProfileRepository.findByCustomerId(customerId).orElseThrow();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public FinancialProfile createFinancialProfile(Long customerId, FinancialProfile financialProfile) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer no encontrado con ID: " + customerId));

        Optional<FinancialProfile> existingProfile = financialProfileRepository.findByCustomerId(customer.getId());
        if (existingProfile.isPresent()) {
            throw new RuntimeException("El Customer ya tiene un FinancialProfile asociado.");
        }

        financialProfile.setCustomer(customer);
        financialProfile.setDeleted(false);
        return financialProfileRepository.save(financialProfile);
    }
}
