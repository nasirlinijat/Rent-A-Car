package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.CustomerService;
import com.example.rentACarProject.business.abstracts.RentHistoryService;
import com.example.rentACarProject.business.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.business.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.business.responses.GetAllCustomerResponse;
import com.example.rentACarProject.business.responses.GetAllRentHistoryResponse;
import com.example.rentACarProject.core.utilities.mapper.abstracts.ModelMapperService;
import com.example.rentACarProject.dataAccess.abstracts.CustomerRepository;
import com.example.rentACarProject.entities.concrates.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final RentHistoryService rentHistoryService;

    @Override
    public void add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = this.modelMapperService
                .forRequest()
                .map(createCustomerRequest,Customer.class);

        this.customerRepository.save(customer);
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        List<Customer> customers = this.customerRepository.findAll();
        List<GetAllCustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers){
            GetAllCustomerResponse customerResponse = this.modelMapperService.forResponse()
                    .typeMap(Customer.class, GetAllCustomerResponse.class)
                    .addMappings(mapper->mapper.skip(GetAllCustomerResponse::setRentHistories))
                    .map(customer);

            List<GetAllRentHistoryResponse> rentHistoryResponses = this.rentHistoryService.getAllByCustomer(customer);
            customerResponse.setRentHistories(rentHistoryResponses);

            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    @Override
    public void delete(int id) {
        this.customerRepository.findById(id).ifPresent(this.customerRepository::delete);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.modelMapperService
                .forRequest()
                .map(updateCustomerRequest,Customer.class);

        this.customerRepository.save(customer);
    }
}
