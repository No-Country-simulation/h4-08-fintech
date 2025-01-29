package com.web.backend.domain.model.financials;

import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.utils.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class FinancialSnapshot extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private float income;
    private float fixedExpenses;
    private float debts;
    private float currentSavings;
    private Date snapshotDate;

    private boolean deleted;
}
