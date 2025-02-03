package com.web.backend.infrastructure.api.utils.investment;


import com.web.backend.application.dto.asset.AssetTypeResponse;
import com.web.backend.application.dto.investment.InvestmentListResponse;
import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.domain.model.investment.Investment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    @Mapping(target = "amount")
    Investment toInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse toInvestmentResponse(Investment investment);
    AssetTypeResponse map(String value);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvestmentFromRequest(InvestmentRequest investmentRequest, @MappingTarget Investment investment);

    @Mapping(target = "asset", source = "asset")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "investmentDate", source = "createdAt")
    @Mapping(target = "maturityDate", source = "maturityDate")
    @Mapping(target = "currentValue", source = "currentValue")
    @Mapping(target = "status", source = "status")
    InvestmentListResponse toInvestmentListResponse(Investment investment);

    @Mapping(target = "asset", source = "asset")
    Investment toInvestmentFromInvestmentListResponse(InvestmentListResponse investmentListResponse);
}
