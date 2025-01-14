package com.web.backend.domain.repository.Financials;

import com.web.backend.domain.model.Financials.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RFinancialProfile extends JpaRepository<FinancialProfile, Long> {
}