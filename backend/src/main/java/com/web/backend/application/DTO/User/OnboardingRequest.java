package com.web.backend.application.DTO.User;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OnboardingRequest(
        String financialKnowledge,
        String riskTolerance,
        Float estimatedIncome,
        Float estimatedExpenses,
        Float savingsCapacity,
        List<Long> financialGoalIds,
        String fullName,
        String phoneNumber
) {
}
