package com.web.backend.application.service.User.onboarding;

import com.web.backend.application.dtos.user.OnboardingRequest;
import com.web.backend.application.service.User.customer.CustomerService;
import com.web.backend.application.service.User.financials.FinancialProfileService;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.financials.FinancialProfile;
import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import com.web.backend.domain.repository.objective.ObjectiveRepository;
import com.web.backend.domain.repository.objective.ObjectiveStatusRepository;
import com.web.backend.infrastructure.api.utils.AccountType;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OnboardingService {

    private final CustomerService customerService;
    private final FinancialProfileService financialProfileService;
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveStatusRepository objectiveStatusRepository;

    public FinancialProfile handleOnboarding(String email, OnboardingRequest request) {
        Customer customer = customerService.findByPhoneNumber(request.phoneNumber())
                .orElseGet(() -> createBasicCustomer(email, request));

        FinancialProfile financialProfile = new FinancialProfile();
        financialProfile.setMainGoal(request.savingsCapacity());
        financialProfile.setTolerance(mapRiskTolerance(request.riskTolerance()));
        financialProfile.setEstimatedIncome(request.estimatedIncome());
        financialProfile.setEstimatedExpenses(request.estimatedExpenses());
        financialProfile.setSavingsCapacity(request.savingsCapacity());
        financialProfile.setStepsCompleted(true);

        try {
            financialProfile = financialProfileService.createFinancialProfile(customer.getId(), financialProfile);
        } catch (RuntimeException e) {
            if (!e.getMessage().contains("ya tiene un FinancialProfile")) {
                throw e;
            }
        }

        // Manejar objetivos financieros
        updateObjectives(customer, request.financialGoalIds());

        return financialProfile;
    }

    private Customer createBasicCustomer(String email, OnboardingRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setFullName(request.fullName() != null ? request.fullName() : "Nombre no proporcionado");
        newCustomer.setPhoneNumber(request.phoneNumber() != null ? request.phoneNumber() : "0000000000");
        newCustomer.setAccountType(AccountType.SAVINGS);
        newCustomer.setDateOfBirth(new Date());

        return customerService.createCustomerUser(email, newCustomer);
    }

    private short mapRiskTolerance(String riskTolerance) {
        return switch (riskTolerance.toLowerCase()) {
            case "conservador" -> 1;
            case "moderado" -> 2;
            case "agresivo" -> 3;
            default -> throw new IllegalArgumentException("Invalid risk tolerance");
        };
    }

    private void updateObjectives(Customer customer, List<Long> financialGoalIds) {
        // Elimina objetivos existentes si es necesario (opcional según el negocio)
        List<Objective> currentObjectives = objectiveRepository.findByObjectiveStatus(null);
        currentObjectives.forEach(objective -> {
            if (!financialGoalIds.contains(objective.getId())) {
                objective.setDeleted(true);
                objectiveRepository.save(objective);
            }
        });

        for (Long goalId : financialGoalIds) {
            ObjectiveStatus status = objectiveStatusRepository.findById(goalId)
                    .orElseThrow(() -> new EntityNotFoundException("ObjectiveStatus not found"));
            Objective objective = Objective.builder()
                    .title(status.getName())
                    .objectiveStatus(status)
                    .currentAmount(0.0f)
                    .targetAmount(10000.0f)
                    .dueDate(null)
                    .deleted(false)
                    .build();
            objectiveRepository.save(objective);
        }
    }
}
