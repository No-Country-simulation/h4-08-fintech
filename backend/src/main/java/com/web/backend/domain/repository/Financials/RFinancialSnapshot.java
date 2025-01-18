package com.web.backend.domain.repository.Financials;

import com.web.backend.domain.model.Addresses.Addresses;
import com.web.backend.domain.model.Financials.FinancialSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RFinancialSnapshot extends JpaRepository<FinancialSnapshot, Long>, JpaSpecificationExecutor<FinancialSnapshot> {
}