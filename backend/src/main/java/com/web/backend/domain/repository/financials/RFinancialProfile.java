package com.web.backend.domain.repository.financials;

import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.financials.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RFinancialProfile extends JpaRepository<FinancialProfile, Long> {
    Optional<FinancialProfile> findByCustomerId(Long customerId);
    Optional<FinancialProfile> findByCustomer(Customer customer);
}