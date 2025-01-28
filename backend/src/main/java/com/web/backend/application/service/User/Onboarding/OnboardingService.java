package com.web.backend.application.service.User.Onboarding;

import com.web.backend.application.service.User.Customer.CustomerService;
import com.web.backend.application.service.User.Financials.FinancialProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OnboardingService {

    private final CustomerService customerService;
    private final FinancialProfileService financialProfileService;

}
