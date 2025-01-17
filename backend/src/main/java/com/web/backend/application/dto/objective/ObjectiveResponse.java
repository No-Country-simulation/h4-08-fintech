package com.web.backend.application.dto.objective;


import java.time.LocalDateTime;

public record ObjectiveResponse(
        Long id,
        String title,
        String description,
        Float targetAmount,
        Float currentAmount,
        LocalDateTime dueDate,
        ObjectiveStatusResponse objectiveStatus
) {
}
