package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.investment.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByInvestmentType(InvestmentType investmentType);
}
