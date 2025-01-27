package com.web.backend.application.DTO.Customer;

import com.web.backend.domain.model.Customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerUserRequest {
    Customer customer;
    String email;
}
