package com.web.backend.application.dto.objective;

import java.util.Date;

public record ObjectiveRequest(
        Long customerId,
        String title,
        String description,
        Float targetAmount,
        Float currentAmount,
        Date dueDate,
        Long objectiveStatusId
) {}
