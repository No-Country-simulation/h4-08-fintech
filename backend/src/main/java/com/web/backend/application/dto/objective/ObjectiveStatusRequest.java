package com.web.backend.application.DTO.objective;

import jakarta.validation.constraints.NotBlank;

public record ObjectiveStatusRequest(
        @NotBlank(message = "El nombre no puede estar en blanco")
        String name
) {}