package com.web.backend.infrastructure.api.utils.investment;


import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.domain.model.investment.Investment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    Investment toInvestment(InvestmentRequest investmentRequest);
    InvestmentResponse toInvestmentResponse(Investment investment);
}
