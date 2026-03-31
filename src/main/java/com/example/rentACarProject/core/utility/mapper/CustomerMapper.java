package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.dto.responses.CustomerResponse;
import com.example.rentACarProject.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);
    Customer toEntity(CreateCustomerRequest request);
    Customer toEntity(UpdateCustomerRequest request);
}