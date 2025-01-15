package com.web.backend.application.service.interfaces.investment;

import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;

import java.util.List;

public interface InvestmentService {
    InvestmentResponse createInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse getInvestmentById(Long id);

    List<InvestmentResponse> getAllInvestments();

    InvestmentResponse updateInvestment(Long id, InvestmentRequest investmentDTO);

    void deleteInvestment(Long id);
}