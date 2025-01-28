package com.web.backend.application.service.impl.customer;

import com.web.backend.application.DTO.customer.CustomerRequest;
import com.web.backend.application.DTO.customer.CustomerResponse;
import com.web.backend.application.DTO.investment.InvestmentResponse;
import com.web.backend.application.DTO.objective.ObjectiveResponse;
import com.web.backend.application.exception.customer.CustomerNotFoundException;
import com.web.backend.application.exception.user.UserNotFoundException;
import com.web.backend.application.service.interfaces.customer.CustomerService;
import com.web.backend.domain.model.Customer.Customer;
import com.web.backend.domain.model.investment.Investment;
import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.repository.Customer.RCustomer;
import com.web.backend.domain.repository.User.RUser;
import com.web.backend.domain.repository.investment.InvestmentRepository;
import com.web.backend.domain.repository.objective.ObjectiveRepository;
import com.web.backend.infrastructure.api.utils.customer.CustomerMapper;
import com.web.backend.infrastructure.api.utils.investment.InvestmentMapper;
import com.web.backend.infrastructure.api.utils.objective.ObjectiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final InvestmentRepository investmentRepository;
    private final InvestmentMapper investmentMapper;
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveMapper objectiveMapper;
    private final CustomerMapper customerMapper;
    private final RCustomer customerRepository;
    private final RUser userRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);

        UserModel userModel = userRepository.findById(customerRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + customerRequest.userId()));
        customer.setUserModel(userModel);

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerMapper.updateCustomerFromRequest(customerRequest, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if(!customerRepository.existsById(id))
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        customerRepository.deleteById(id);
    }

    public List<InvestmentResponse> getInvestmentsByCustomerId(Long customerId, String sortBy, String orderBy) {
        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        List<Investment> investments = investmentRepository.findByCustomerId(customerId, sort);

        return investments.stream().map(investmentMapper::toInvestmentResponse).toList();
    }

    public List<ObjectiveResponse> getObjectivesByCustomerId(Long customerId, String sortBy, String orderBy) {
        Sort.Direction direction = orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        List<Objective> objectives = objectiveRepository.findByCustomerId(customerId, sort);

        return objectives.stream().map(objectiveMapper::toObjectiveResponse).toList();
    }
}
