package com.web.backend.application.dto.investment;

import com.web.backend.domain.model.investment.InvestmentType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public record InvestmentRequest(
        Long customerId,
        Long investmentTypeId,
        Float amount,
        Date maturityDate,
        Float currentValue,
        String status
) {
}
