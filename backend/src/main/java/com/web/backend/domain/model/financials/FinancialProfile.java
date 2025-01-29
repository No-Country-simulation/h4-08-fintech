package com.web.backend.domain.model.financials;

import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.utils.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class FinancialProfile extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private Customer customer;

    private float mainGoal;
    private short tolerance;
    private float estimatedIncome;
    private float estimatedExpenses;
    private float savingsCapacity;
    private boolean stepsCompleted;

    private boolean deleted;
}
