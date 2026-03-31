package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.dto.responses.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAll();
    CustomerResponse getById(Long id);
    CustomerResponse add(CreateCustomerRequest request);
    CustomerResponse update(Long id, UpdateCustomerRequest request);
    void delete(Long id);
}