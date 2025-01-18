package com.web.backend.domain.repository.Financials;

import com.web.backend.domain.model.Addresses.Addresses;
import com.web.backend.domain.model.Financials.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RFinancialProfile extends JpaRepository<FinancialProfile, Long>, JpaSpecificationExecutor<FinancialProfile> {
}