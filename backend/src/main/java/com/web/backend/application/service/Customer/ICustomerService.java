package com.web.backend.application.service.Customer;

import com.web.backend.domain.model.Customer.Customer;

public interface ICustomerService {
    Customer createCustomerUser(String email,Customer newCuatomer);
}
