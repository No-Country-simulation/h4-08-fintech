package com.web.backend.domain.repository.User;

import com.web.backend.domain.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RUser extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByCustomer_PhoneNumber(String phoneNumber);
    Optional<UserModel> findByEmail(String email);
}
