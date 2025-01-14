package com.web.backend.application.dto.investment;

import com.web.backend.domain.model.investment.InvestmentType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public record InvestmentResponse(
        Long id,
//            Customer customer;
        InvestmentType investmentType,
        Float amount,
        Date investmentDate,
        Date maturityDate,
        Float currentValue,
        String status
) {
}
