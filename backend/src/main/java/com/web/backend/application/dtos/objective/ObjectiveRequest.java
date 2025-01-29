package com.web.backend.application.dtos.objective;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ObjectiveRequest(
        @NotNull(message = "El cliente no puede ser null")
        Long customerId,
        @NotBlank(message = "El titulo no puede estar en blanco")
        String title,
        String description,
        Float targetAmount,
        Float currentAmount,
        LocalDateTime dueDate,
        Long objectiveStatusId
) {}
