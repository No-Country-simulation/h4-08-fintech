package com.web.backend.application.service.impl.invesment;

import com.web.backend.application.DTO.investment.InvestmentRequest;
import com.web.backend.application.DTO.investment.InvestmentResponse;
import com.web.backend.application.exception.asset.AssetNotFoundException;
import com.web.backend.application.exception.customer.CustomerNotFoundException;
import com.web.backend.application.exception.customer.InsufficientBalanceException;
import com.web.backend.application.exception.investment.InvestmentNotFoundException;
import com.web.backend.application.service.interfaces.investment.InvestmentService;
import com.web.backend.domain.model.assetTemp.AssetTemp;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.repository.assetTemp.RAssentTemp;
import com.web.backend.domain.repository.customer.RCustomer;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import com.web.backend.infrastructure.api.utils.investment.InvestmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final RAssentTemp assetRepository;
    private final InvestmentMapper investmentMapper;
    private final RCustomer customerRepository;

    public InvestmentResponse createInvestment(InvestmentRequest investmentRequest) {
        Investment investment = investmentMapper.toInvestment(investmentRequest);

        AssetTemp asset = assetRepository.findById(investmentRequest.assetId())
                        .orElseThrow(() -> new AssetNotFoundException("Asset not found with id: " + investmentRequest.assetId()));
//        investment.setAsset(asset);

        Customer customer = customerRepository.findById(investmentRequest.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + investmentRequest.customerId()));
        investment.setCustomer(customer);

        if(customer.getBalance() < investment.getAmount() * asset.getCurrentPrice())
            throw new InsufficientBalanceException("Customer with insufficient balance");

        investmentRepository.save(investment);
        return investmentMapper.toInvestmentResponse(investment);
    }

    public InvestmentResponse getInvestmentById(Long id) {
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(() -> new InvestmentNotFoundException("Investment not found with id: " + id));
        return investmentMapper.toInvestmentResponse(investment);
    }

    @Override
    public List<InvestmentResponse> getInvestmentsByDeleted(boolean deleted) {
        List<Investment> investments = investmentRepository.findAllByIsDeleted(deleted);
        return investments.stream().map(investmentMapper::toInvestmentResponse).toList();
    }

    @Override
    public InvestmentResponse updateInvestment(Long id, InvestmentRequest investmentRequest) {
        Investment existingInvestment = investmentRepository.findById(id)
                .orElseThrow(() -> new InvestmentNotFoundException("Investment not found with id: " + id));

        investmentMapper.updateInvestmentFromRequest(investmentRequest, existingInvestment);

        if(investmentRequest.customerId()!= null) {
            Customer customer = customerRepository.findById(investmentRequest.customerId())
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + investmentRequest.customerId()));
            existingInvestment.setCustomer(customer);
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