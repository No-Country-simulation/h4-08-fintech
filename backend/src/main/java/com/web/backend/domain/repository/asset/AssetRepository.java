package com.web.backend.domain.repository.asset;

import com.web.backend.domain.model.asset.Asset;
import com.web.backend.domain.model.asset.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, String> {
    List<Asset> findAllByAssetType(AssetType assetType);

    @Query(value = "SELECT * FROM asset a WHERE a.deleted = :deleted AND (LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.ticker) LIKE LOWER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    List<Asset> searchByDeletedAndKeyword(@Param("deleted") boolean deleted, @Param("keyword") String keyword);
}
