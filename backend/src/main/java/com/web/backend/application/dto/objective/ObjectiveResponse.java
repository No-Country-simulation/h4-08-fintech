package com.web.backend.application.dto.objective;

import com.web.backend.domain.model.objective.ObjectiveStatus;

import java.util.Date;

public record ObjectiveResponse(
        Long id,
        String title,
        String description,
        Float targetAmount,
        Float currentAmount,
        Date dueDate,
        ObjectiveStatus objectiveStatus,
        boolean isDeleted
) {
}
