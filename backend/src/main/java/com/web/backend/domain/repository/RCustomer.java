package com.web.backend.domain.repository;

import com.web.backend.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RCustomer extends JpaRepository<Customer, Long> {
}