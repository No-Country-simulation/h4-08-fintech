package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.InvestmentType;
import com.web.backend.domain.model.investment.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestmentTypeRepository extends JpaRepository<InvestmentType, Long> {
    @Query(value = "SELECT * FROM Investment_Type i WHERE i.deleted = :deleted", nativeQuery = true)
    List<InvestmentType> findAllByIsDeleted(@Param("deleted") boolean isDeleted);
}
