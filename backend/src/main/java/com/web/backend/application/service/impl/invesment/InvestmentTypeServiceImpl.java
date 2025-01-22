package com.web.backend.application.service.impl.invesment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;
import com.web.backend.application.exception.investment.InvestmentTypeNotFoundException;
import com.web.backend.application.service.interfaces.investment.InvestmentTypeService;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.investment.InvestmentType;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import com.web.backend.domain.repository.investment.InvestmentTypeRepository;
import com.web.backend.infrastructure.api.utils.investment.InvestmentTypeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentTypeServiceImpl implements InvestmentTypeService {
    
    private final InvestmentTypeRepository investmentTypeRepository;
    private final InvestmentRepository investmentRepository;
    private final InvestmentTypeMapper investmentTypeMapper;

    @Override
    public InvestmentTypeResponse createInvestmentType(InvestmentTypeRequest investmentTypeRequest) {
        InvestmentType investmentType = investmentTypeMapper.toInvestmentType(investmentTypeRequest);
        investmentTypeRepository.save(investmentType);
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    public InvestmentTypeResponse getInvestmentTypeById(Long id) {
        InvestmentType investmentType = investmentTypeRepository.findById(id)
                .orElseThrow(() -> new InvestmentTypeNotFoundException("Investment Type not found with id: " + id));
        if (investmentType.isDeleted()) {
            throw new IllegalStateException("The investment type with id: " + id + " has been deleted.");
        }
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    public List<InvestmentTypeResponse> getInvestmentTypesByDeleted(boolean deleted) {
        List<InvestmentType> investmentTypes = investmentTypeRepository.findAllByIsDeleted(deleted);
        return investmentTypes.stream().map(investmentTypeMapper::toInvestmentTypeResponse).toList();
    }

    @Override
    public InvestmentTypeResponse updateInvestmentType(Long id, InvestmentTypeRequest investmentTypeRequest) {
        InvestmentType investmentType = investmentTypeRepository.findById(id)
                .orElseThrow(() -> new InvestmentTypeNotFoundException("Investment Type not found with id: " + id));
        investmentTypeMapper.updateInvestmentTypeFromRequest(investmentTypeRequest, investmentType);
        investmentTypeRepository.save(investmentType);
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    @Transactional
    public void deleteInvestmentType(Long id) {
        InvestmentType investmentType = investmentTypeRepository.findById(id)
                .orElseThrow(() -> new InvestmentTypeNotFoundException("Investment Type not found with id: " + id));

        List<Investment> investments = investmentRepository.findByInvestmentType(investmentType);
        for (Investment investment : investments) {
            investment.setInvestmentType(null);
        }
        investmentRepository.saveAll(investments);

        investmentTypeRepository.deleteById(id);;
    }
}
