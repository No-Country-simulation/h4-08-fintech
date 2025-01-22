package com.web.backend.domain.repository.AssetTemp;

import com.web.backend.domain.model.AssetTemp.AssetTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RAssentTemp extends JpaRepository<AssetTemp, Long> {
    @Query("SELECT MAX(a.potentialReturns) FROM AssetTemp a")
    Double findMaxPotentialReturns();
    boolean existsByTikerSymbol(String symbol);
}