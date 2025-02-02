package com.web.backend.application.service.User.customer;

import com.web.backend.application.service.User.UserService;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.repository.customer.RCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final RCustomer repository;
    private final UserService userService;

    public Customer getById(Long customerId){
        try{

            return repository.findById(customerId).orElseThrow();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getByEmail(String email) {
        return repository.findByUserModelEmail(email).orElse(null);
    }

    public Customer createCustomerUser(String email, Customer newCustomer) {
        Optional<Customer> customerExist = repository.findByPhoneNumber(newCustomer.getPhoneNumber());
        if (customerExist.isPresent()) {
            throw new RuntimeException("El customer ya existe");
        }

        Optional<UserModel> userExist = Optional.ofNullable(userService.getDetailUser(email));
        if (userExist.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        UserModel userModel = userExist.get();
        System.out.println("createCUSTOMER :" + userModel.getId());
        System.out.println(newCustomer.getPhoneNumber());
        newCustomer.setUserModel(userModel);

        return repository.save(newCustomer);
    }
    public Optional<Customer> findByPhoneNumber(String phone){
        return repository.findByPhoneNumber(phone);
    }
}

