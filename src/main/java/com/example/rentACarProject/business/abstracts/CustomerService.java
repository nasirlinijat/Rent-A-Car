package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.business.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.business.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.business.responses.GetAllCustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void add(CreateCustomerRequest createCustomerRequest);
    List<GetAllCustomerResponse> getAll();
    void delete(int id);
    void update(UpdateCustomerRequest updateCustomerRequest);
}
