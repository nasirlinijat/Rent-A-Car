package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerBusinessRules {

    private final CustomerRepository customerRepository;

    public void checkIfEmailExists(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new BusinessException("Customer with email '" + email + "' already exists");
        }
    }

    public void checkIfCustomerExists(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new BusinessException("Customer with id " + id + " does not exist");
        }
    }
}
