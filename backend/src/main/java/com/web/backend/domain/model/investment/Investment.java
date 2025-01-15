package com.web.backend.domain.model.investment;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Investment {
    @Id
    @GeneratedValue
    private Long id;
//    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "investment_type_id")
    private InvestmentType investmentType;
    private Float amount;
    @Column(name = "investment_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date investmentDate;
    @Column(name = "maturity_date")
    private Date maturityDate;
    @Column(name = "current_value")
    private Float currentValue;
    private String status;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
