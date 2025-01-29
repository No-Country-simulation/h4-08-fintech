package com.web.backend.domain.model.addresses;

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
public class Addresses extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String address;
    private String currency;

    private boolean deleted;
}
