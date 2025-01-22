package com.web.backend.domain.repository.investment;

import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.asset.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    @Query(value = "SELECT * FROM investment i WHERE i.deleted = :deleted", nativeQuery = true)
    List<Investment> findAllByIsDeleted(@Param("deleted") boolean deleted);
}
