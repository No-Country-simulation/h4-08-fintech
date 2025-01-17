package com.web.backend.domain.model.Financials;

import com.web.backend.domain.model.Customer.Customer;
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
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private float main_goal;
    private short tolerance;
    private float estimated_income;
    private float estimated_expenses;
    private float savings_capacity;
    private boolean stepsCompleted;

    private boolean deleted;
}
