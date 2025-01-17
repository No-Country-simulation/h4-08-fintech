package com.web.backend.application.dto.objective;


import java.util.Date;

public record ObjectiveResponse(
        Long id,
        String title,
        String description,
        Float targetAmount,
        Float currentAmount,
        Date dueDate,
        ObjectiveStatusResponse objectiveStatus
) {
}
