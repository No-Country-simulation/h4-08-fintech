package com.web.backend.application.service.investment;

import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvestmentAnalitics {

    private final InvestmentRepository investmentRepository;

    public List<Investment> getBestOptionInvestment(){
        try{



        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
