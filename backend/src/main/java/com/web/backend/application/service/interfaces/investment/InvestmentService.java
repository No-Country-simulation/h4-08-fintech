package com.web.backend.application.service.interfaces.investment;

import com.web.backend.application.dtos.investment.InvestmentRequest;
import com.web.backend.application.dtos.investment.InvestmentResponse;

import java.util.List;

public interface InvestmentService {
    InvestmentResponse createInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse getInvestmentById(Long id);

    List<InvestmentResponse> getInvestmentsByDeleted(boolean deleted);

    InvestmentResponse updateInvestment(Long id, InvestmentRequest investmentDTO);

    void deleteInvestment(Long id);
}