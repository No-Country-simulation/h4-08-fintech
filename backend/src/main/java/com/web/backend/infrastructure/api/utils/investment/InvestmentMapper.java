package com.web.backend.infrastructure.api.utils.investment;


import com.web.backend.application.dtos.asset.AssetTypeResponse;
import com.web.backend.application.dtos.investment.InvestmentRequest;
import com.web.backend.application.dtos.investment.InvestmentResponse;
import com.web.backend.domain.model.investment.Investment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    @Mapping(target = "amount")
    Investment toInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse toInvestmentResponse(Investment investment);
    AssetTypeResponse map(String value);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvestmentFromRequest(InvestmentRequest investmentRequest, @MappingTarget Investment investment);
}
