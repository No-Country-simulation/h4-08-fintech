package com.web.backend.domain.repository.asset;

import com.web.backend.domain.model.asset.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {
    @Query(value = "SELECT * FROM Asset_Type a WHERE a.deleted = :deleted", nativeQuery = true)
    List<AssetType> findAllByIsDeleted(@Param("deleted") boolean isDeleted);
}
