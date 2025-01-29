package com.web.backend.application.dto.user;

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
