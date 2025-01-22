package com.web.backend.domain.model.Customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.utils.Auditable;
import com.web.backend.infrastructure.api.utils.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_model_id", nullable = false)
    private UserModel userModel;

    @NotBlank
    private String fullName;
    private float balance;
    @NotNull
    private AccountType accountType;
    @NotNull
    private Date dateOfBirth;
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    private boolean deleted = false;
}