package com.web.backend.domain.repository.User;

import com.web.backend.domain.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RUser extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
    Optional<UserModel> findByCustomer_PhoneNumber(String phoneNumber);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsername(String username);
}
