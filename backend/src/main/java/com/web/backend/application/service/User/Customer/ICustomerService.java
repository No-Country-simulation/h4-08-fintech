package com.web.backend.application.service.User.Customer;

import com.web.backend.domain.model.Customer.Customer;

public interface ICustomerService {
    Customer createCustomerUser(String email,Customer newCuatomer);
}
