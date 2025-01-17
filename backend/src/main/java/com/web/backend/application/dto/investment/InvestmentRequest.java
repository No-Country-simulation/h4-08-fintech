package com.web.backend.application.dto.investment;

import com.web.backend.domain.model.investment.InvestmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public record InvestmentRequest(
        @NotNull(message = "El cliente no puede estar en blanco")
        Long customerId,
        Long investmentTypeId,
        Float amount,
        Date maturityDate,
        Float currentValue,
        String status
) {
}
