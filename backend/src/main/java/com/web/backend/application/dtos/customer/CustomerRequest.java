package com.web.backend.application.dtos.customer;


import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CustomerRequest (
    String name,
    String email,
    @NotNull(message = "El id del usuario no puede ser null")
    Long userId,
    String fullName,
    float balance,
    Date dateOfBirth,
    String phoneNumber
) {}