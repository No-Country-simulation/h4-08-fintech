package com.web.backend.domain.model.AssetTemp;

import com.web.backend.domain.utils.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@SQLDelete(sql = "UPDATE asset_temp SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedAssetTempFilter", parameters = @ParamDef(name = "isDeleted",type = Boolean.class))
@Filter(name = "deletedAssetTempFilter", condition = "deleted = :isDeleted")
public class AssetTemp extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assetName;
    private String assetType;
    private String sector;
    private int riskLevel;
    private double currentPrice;
    private String currency;

    private boolean deleted = false;

}
