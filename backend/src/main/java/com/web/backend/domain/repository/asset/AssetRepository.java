package com.web.backend.domain.repository.asset;

import com.web.backend.domain.model.asset.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, String> {
    @Query(value = "SELECT * FROM asset a WHERE a.deleted = :deleted", nativeQuery = true)
    List<Asset> findAssetsByDeleted(boolean deleted);
}
