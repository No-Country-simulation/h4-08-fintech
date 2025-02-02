package com.web.backend.application.service.interfaces.investment;

import com.web.backend.application.dto.investment.InvestmentListResponse;
import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.application.dto.investment.SInvestmentListResponse;

import java.util.List;

public interface InvestmentService {

    SInvestmentListResponse getAllInvestmentCustomer(Long customerId, int limit, int page, String sortBy, boolean ascending);

    InvestmentResponse createInvestment(InvestmentRequest investmentRequest);

    InvestmentResponse getInvestmentById(Long id);

    List<InvestmentResponse> getInvestmentsByDeleted(boolean deleted);

    InvestmentResponse updateInvestment(Long id, InvestmentRequest investmentDTO);

    void deleteInvestment(Long id);
}