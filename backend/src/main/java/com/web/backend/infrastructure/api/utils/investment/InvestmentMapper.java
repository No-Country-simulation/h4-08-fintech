package com.web.backend.infrastructure.api.utils.investment;


import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.application.dto.news.NewsSourceRequest;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.news.NewsSource;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    @Mapping(target = "amount", defaultValue = "0.00f")
    Investment toInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse toInvestmentResponse(Investment investment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvestmentFromRequest(InvestmentRequest investmentRequest, @MappingTarget Investment investment);
}
