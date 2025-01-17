package com.web.backend.application.service.impl.invesment;

import com.web.backend.application.dto.investment.InvestmentRequest;
import com.web.backend.application.dto.investment.InvestmentResponse;
import com.web.backend.application.exception.investment.InvestmentNotFoundException;
import com.web.backend.application.exception.investment.InvestmentTypeNotFoundException;
import com.web.backend.application.service.interfaces.investment.InvestmentService;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.investment.InvestmentType;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import com.web.backend.domain.repository.investment.InvestmentTypeRepository;
import com.web.backend.infrastructure.api.utils.investment.InvestmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InvestmentMapper investmentMapper;
    private final InvestmentTypeRepository investmentTypeRepository;

    public InvestmentResponse createInvestment(InvestmentRequest investmentRequest) {
        Investment investment = investmentMapper.toInvestment(investmentRequest);

        if (investmentRequest.investmentTypeId() != null) {
            InvestmentType investmentType = investmentTypeRepository.findById(investmentRequest.investmentTypeId())
                    .orElseThrow(() -> new InvestmentTypeNotFoundException("Investment Type not found with id: " + investmentRequest.investmentTypeId()));
            investment.setInvestmentType(investmentType);
        }

        investmentRepository.save(investment);
        return investmentMapper.toInvestmentResponse(investment);
    }

    public InvestmentResponse getInvestmentById(Long id) {
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(() -> new InvestmentNotFoundException("Investment not found with id: " + id));
        return investmentMapper.toInvestmentResponse(investment);
    }

    @Override
    public List<InvestmentResponse> getAllInvestments() {
        List<Investment> investments = investmentRepository.findAll();
        return investments.stream().map(investmentMapper::toInvestmentResponse).toList();
    }

    @Override
    public InvestmentResponse updateInvestment(Long id, InvestmentRequest investmentRequest) {
        Investment existingInvestment = investmentRepository.findById(id)
                .orElseThrow(() -> new InvestmentNotFoundException("Investment not found with id: " + id));

        investmentMapper.updateInvestmentFromRequest(investmentRequest, existingInvestment);

        if (investmentRequest.investmentTypeId() != null) {
            InvestmentType investmentType = investmentTypeRepository.findById(investmentRequest.investmentTypeId())
                    .orElseThrow(() -> new InvestmentTypeNotFoundException("Investment Type not found with id: " + investmentRequest.investmentTypeId()));
            existingInvestment.setInvestmentType(investmentType);
        }

        investmentRepository.save(existingInvestment);
        return investmentMapper.toInvestmentResponse(existingInvestment);
    }

    @Override
    public void deleteInvestment(Long id) {
        if (!investmentRepository.existsById(id)) {
            throw new InvestmentNotFoundException("Investment not found with id: " + id);
        }
        investmentRepository.deleteById(id);
    }
}