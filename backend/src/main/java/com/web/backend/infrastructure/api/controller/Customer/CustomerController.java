package com.web.backend.infrastructure.api.controller.Customer;

import com.web.backend.application.DTO.Customer.CustomerUserRequest;
import com.web.backend.application.service.Customer.CustomerService;
import com.web.backend.domain.model.Customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/user")
    public ResponseEntity<Customer> createCustomerWithUser(@RequestBody CustomerUserRequest customerRequest){

        Customer createCustomer = customerService.createCustomerUser(customerRequest.getEmail(),customerRequest.getCustomer());

        return  ResponseEntity.ok(createCustomer);
    }

}
