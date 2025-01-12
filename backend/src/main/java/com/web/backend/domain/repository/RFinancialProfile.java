package com.web.backend.domain.repository;

import com.web.backend.domain.model.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RFinancialProfile extends JpaRepository<FinancialProfile, Long> {
}