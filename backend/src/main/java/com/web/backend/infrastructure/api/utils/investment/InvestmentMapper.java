package com.web.backend.infrastructure.api.utils.investment;


import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.news.NewsSource;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    Investment toInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse toInvestmentResponse(Investment investment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvestmentFromRequest(InvestmentRequest investmentRequest, @MappingTarget Investment investment);
}
