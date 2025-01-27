package com.web.backend.domain.model.investment;

import com.web.backend.domain.model.Customer.Customer;
import com.web.backend.domain.model.asset.Asset;
import com.web.backend.domain.model.asset.AssetType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;

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
public class Investment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Float amount;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    @Column(name = "investment_date", updatable = false)
    @CreatedDate
    private LocalDateTime investmentDate;
    @Column(name = "maturity_date")
    private LocalDateTime maturityDate;
    @Column(name = "current_value")
    private Float currentValue;
    private String status;
    private boolean deleted = false;
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
}
