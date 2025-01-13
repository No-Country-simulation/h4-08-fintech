package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentTypeRepository extends JpaRepository<InvestmentType, Long> {
}
