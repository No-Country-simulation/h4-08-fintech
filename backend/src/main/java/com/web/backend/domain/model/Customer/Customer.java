package com.web.backend.domain.model.Customer;

import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.utils.Auditable;
import com.web.backend.infrastructure.api.utils.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private UserModel userModel;
    private String fullName;
    private float balance;
    private AccountType accountType;
    private Date dateOfBirth;
    private String phoneNumber;

    private boolean deleted;

}
