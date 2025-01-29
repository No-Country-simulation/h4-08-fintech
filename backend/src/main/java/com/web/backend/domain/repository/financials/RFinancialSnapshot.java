package com.web.backend.domain.repository.financials;

import com.web.backend.domain.model.financials.FinancialSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RFinancialSnapshot extends JpaRepository<FinancialSnapshot, Long> {
}