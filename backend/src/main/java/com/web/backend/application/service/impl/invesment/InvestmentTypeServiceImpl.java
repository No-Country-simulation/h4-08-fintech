package com.web.backend.application.service.impl.invesment;

import com.web.backend.application.dto.investment.InvestmentTypeRequest;
import com.web.backend.application.dto.investment.InvestmentTypeResponse;
import com.web.backend.application.service.interfaces.investment.InvestmentTypeService;
import com.web.backend.domain.model.investment.InvestmentType;
import com.web.backend.domain.repository.investment.InvestmentTypeRepository;
import com.web.backend.infrastructure.api.utils.investment.InvestmentTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentTypeServiceImpl implements InvestmentTypeService {
    
    private final InvestmentTypeRepository investmentTypeRepository;
    private final InvestmentTypeMapper investmentTypeMapper;

    @Override
    public InvestmentTypeResponse createInvestmentType(InvestmentTypeRequest investmentTypeRequest) {
        InvestmentType investmentType = investmentTypeMapper.toInvestmentType(investmentTypeRequest);
        investmentTypeRepository.save(investmentType);
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    public InvestmentTypeResponse getInvestmentTypeById(Long id) {
        InvestmentType investmentType = investmentTypeRepository.getReferenceById(id);
        if (investmentType.isDeleted()) {
            throw new IllegalStateException("The investment type has been deleted.");
        }
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    public List<InvestmentTypeResponse> getAllInvestmentTypes() {
        List<InvestmentType> investmentTypes = investmentTypeRepository.findAll();
        return investmentTypes.stream().map(investmentTypeMapper::toInvestmentTypeResponse).toList();
    }

    @Override
    public InvestmentTypeResponse updateInvestmentType(Long id, InvestmentTypeRequest investmentTypeRequest) {
        InvestmentType investmentType = investmentTypeRepository.getReferenceById(id);
        if(investmentTypeRequest.name() != null)
            investmentType.setName(investmentTypeRequest.name());
        return investmentTypeMapper.toInvestmentTypeResponse(investmentType);
    }

    @Override
    public void deleteInvestmentType(Long id) {
        InvestmentType investmentType = investmentTypeRepository.getReferenceById(id);
        investmentType.setDeleted(true);
        investmentTypeRepository.save(investmentType);
    }
}
