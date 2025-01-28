package com.web.backend.domain.repository.AssetTemp;

import com.web.backend.domain.model.AssetTemp.AssetTemp;
import com.web.backend.domain.model.asset.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RAssentTemp extends JpaRepository<AssetTemp, String> {
    @Query("SELECT MAX(a.potentialReturns) FROM AssetTemp a")
    Double findMaxPotentialReturns();
    boolean existsByTickerSymbol(String symbol);

    List<AssetTemp> findAllByAssetType(AssetType assetType);

    @Query(value = "SELECT * FROM asset_Temp a WHERE a.deleted = :deleted AND (LOWER(a.asset_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.ticker_symbol) LIKE LOWER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    List<AssetTemp> searchByDeletedAndKeyword(@Param("deleted") boolean deleted, @Param("keyword") String keyword);

}