package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.CustomerService;
import com.example.rentACarProject.business.rules.CustomerBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.CustomerMapper;
import com.example.rentACarProject.dto.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.dto.responses.CustomerResponse;
import com.example.rentACarProject.repository.CustomerRepository;
import com.example.rentACarProject.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerBusinessRules customerBusinessRules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer with id " + id + " does not exist"));
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse add(CreateCustomerRequest request) {
        customerBusinessRules.checkIfEmailExists(request.getEmail());
        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponse(saved);
    }

    @Override
    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        customerBusinessRules.checkIfCustomerExists(id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer with id " + id + " does not exist"));
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setEmail(request.getEmail());
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer with id " + id + " does not exist"));
        customerRepository.delete(customer);
    }
}