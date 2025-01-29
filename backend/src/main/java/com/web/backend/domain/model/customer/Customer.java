package com.web.backend.domain.model.customer;

import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.utils.Auditable;
import com.web.backend.infrastructure.api.utils.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE customer SET deleted = true where id = ?")
@SQLRestriction("deleted=false")
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_model_id", nullable = false)
    private UserModel userModel;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments = new ArrayList<>();

    @NotBlank
    private String fullName;
    private Double balance;
    @NotNull
    private AccountType accountType;
    @NotNull
    private Date dateOfBirth;
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    private boolean deleted = false;
}