package com.web.backend.domain.model.investment;

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
//    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "investment_type_id")
    private InvestmentType investmentType;
    private Float amount;
    @Column(name = "investment_date", updatable = false)
    @CreatedDate
    private LocalDateTime investmentDate;
    @Column(name = "maturity_date")
    private LocalDateTime maturityDate;
    @Column(name = "current_value")
    private Float currentValue;
    private String status;
    private boolean deleted = false;
}
