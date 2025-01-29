package com.web.backend.application.dto.customerrr;

import com.web.backend.domain.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerUserRequest {
    Customer customer;
    String email;
}
