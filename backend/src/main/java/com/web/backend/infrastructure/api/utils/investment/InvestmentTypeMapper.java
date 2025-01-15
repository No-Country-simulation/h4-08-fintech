package com.web.backend.infrastructure.api.utils.investment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;
import com.web.backend.domain.model.investment.InvestmentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentTypeMapper {
    InvestmentType toInvestmentType(InvestmentTypeRequest investmentTypeRequest);
    InvestmentTypeResponse toInvestmentTypeResponse(InvestmentType investmentType);
}
