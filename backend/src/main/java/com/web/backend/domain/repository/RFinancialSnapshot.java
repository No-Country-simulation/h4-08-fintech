package com.web.backend.domain.repository;

import com.web.backend.domain.model.FinancialSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RFinancialSnapshot extends JpaRepository<FinancialSnapshot, Long> {
}