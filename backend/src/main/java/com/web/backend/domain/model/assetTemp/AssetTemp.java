package com.web.backend.domain.model.assetTemp;

import com.web.backend.domain.utils.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@SQLDelete(sql = "UPDATE asset_temp SET deleted = true WHERE id = ?")
@SQLRestriction("deleted=false")
public class AssetTemp extends Auditable {

    @Id
    private String tickerSymbol;
    private String assetName;
    private String assetType;
    private String sector;
    private int riskLevel;
    private double currentPrice;
    private float potentialReturns;
    private String currency;

    private boolean deleted = false;

}
