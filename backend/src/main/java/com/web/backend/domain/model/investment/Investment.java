package com.web.backend.domain.model.investment;

import com.web.backend.domain.model.assetTemp.AssetTemp;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.utils.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SQLDelete(sql = "UPDATE investment SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class Investment extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetTemp asset;
    @Column(name = "maturity_date")
    private LocalDateTime maturityDate;
    @Column(name = "current_value")
    private Double currentValue;
    private String status;
    private boolean deleted = false;
}
