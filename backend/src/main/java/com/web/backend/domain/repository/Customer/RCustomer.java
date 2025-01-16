package com.web.backend.domain.repository.Customer;

import com.web.backend.domain.model.Customer.Customer;
import com.web.backend.domain.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RCustomer extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}