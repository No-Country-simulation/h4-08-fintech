package com.web.backend.domain.model.investment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InvestmentType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
