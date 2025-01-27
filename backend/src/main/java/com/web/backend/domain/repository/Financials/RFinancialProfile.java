package com.web.backend.domain.repository.Financials;

import com.web.backend.domain.model.Addresses.Addresses;
import com.web.backend.domain.model.Customer.Customer;
import com.web.backend.domain.model.Financials.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RFinancialProfile extends JpaRepository<FinancialProfile, Long> {
    Optional<FinancialProfile> findByCustomerId(Long customerId);
    Optional<FinancialProfile> findByCustomer(Customer customer);
}