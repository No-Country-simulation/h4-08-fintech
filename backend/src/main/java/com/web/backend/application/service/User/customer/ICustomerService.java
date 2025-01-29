package com.web.backend.application.service.User.customer;

import com.web.backend.domain.model.customer.Customer;

public interface ICustomerService {
    Customer createCustomerUser(String email,Customer newCuatomer);
}
