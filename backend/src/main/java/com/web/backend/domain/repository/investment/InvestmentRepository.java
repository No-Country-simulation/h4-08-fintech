package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
