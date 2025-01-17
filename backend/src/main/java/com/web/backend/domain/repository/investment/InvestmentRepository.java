package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.investment.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByInvestmentType(InvestmentType investmentType);

    @Query(value = "SELECT * FROM investment i WHERE i.deleted = :deleted", nativeQuery = true)
    List<Investment> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
