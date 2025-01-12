package com.web.backend.domain.repository;

import com.web.backend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RUser extends JpaRepository<User, Long> {
    Optional<RUser> findByCustomer_PhoneNumber(String phoneNumber);
}
