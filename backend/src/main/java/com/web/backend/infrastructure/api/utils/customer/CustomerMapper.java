package com.web.backend.infrastructure.api.utils.customer;

import com.web.backend.application.dto.customerrr.CustomerRequest;
import com.web.backend.application.dto.customerrr.CustomerResponse;
import com.web.backend.domain.model.customer.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromRequest(CustomerRequest customerRequest, @MappingTarget Customer customer);
}

