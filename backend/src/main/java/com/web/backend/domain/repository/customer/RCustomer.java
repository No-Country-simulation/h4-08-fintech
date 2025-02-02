package com.web.backend.domain.repository.customer;

import com.web.backend.domain.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RCustomer extends JpaRepository<Customer, Long>{
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    Optional<Customer> findByUserModelEmail(String email);
}