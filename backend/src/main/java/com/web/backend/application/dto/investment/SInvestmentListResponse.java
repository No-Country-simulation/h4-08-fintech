package com.web.backend.application.dto.investment;

import lombok.Builder;

import java.util.List;

@Builder
public class SInvestmentListResponse {
    public List<InvestmentListResponse> listResponse;
    public Double totalTransactions;
}
