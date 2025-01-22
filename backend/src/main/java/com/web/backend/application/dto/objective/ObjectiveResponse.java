package com.web.backend.application.DTO.objective;


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
