package com.web.backend.application.service.interfaces.investment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;

import java.util.List;

public interface InvestmentTypeService {
    InvestmentTypeResponse createInvestmentType(InvestmentTypeRequest InvestmentTypeRequest);

    InvestmentTypeResponse getInvestmentTypeById(Long id);

    List<InvestmentTypeResponse> getAllInvestmentTypes();

    InvestmentTypeResponse updateInvestmentType(Long id, InvestmentTypeRequest InvestmentTypeDTO);

    void deleteInvestmentType(Long id);
}
