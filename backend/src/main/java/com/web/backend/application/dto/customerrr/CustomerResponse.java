package com.web.backend.application.dto.customerrr;



import java.util.Date;

public record CustomerResponse (
    Long id,
    String name,
    String email,
    String fullName,
    float balance,
    Date dateOfBirth,
    String phoneNumber
) {}