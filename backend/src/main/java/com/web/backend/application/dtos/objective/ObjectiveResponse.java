package com.web.backend.application.dtos.objective;


import java.time.LocalDateTime;

public record ObjectiveResponse(
        Long id,
        String title,
        String description,
        Float targetAmount,
        int progress,
        Float currentAmount,
        LocalDateTime dueDate,
        ObjectiveStatusResponse objectiveStatus
) {
}
