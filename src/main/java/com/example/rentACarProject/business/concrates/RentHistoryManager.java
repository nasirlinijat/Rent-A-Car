package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.RentHistoryService;
import com.example.rentACarProject.business.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.business.responses.GetAllRentHistoryResponse;
import com.example.rentACarProject.core.utilities.mapper.abstracts.ModelMapperService;
import com.example.rentACarProject.dataAccess.abstracts.CarRepository;
import com.example.rentACarProject.dataAccess.abstracts.CustomerRepository;
import com.example.rentACarProject.dataAccess.abstracts.RentHistoryRepository;
import com.example.rentACarProject.entities.concrates.Car;
import com.example.rentACarProject.entities.concrates.Customer;
import com.example.rentACarProject.entities.concrates.RentHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RentHistoryManager implements RentHistoryService {
    private final ModelMapperService modelMapperService;
    private final RentHistoryRepository rentHistoryRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Override
    public void add(CreateRentHistoryRequest createRentHistoryRequest) {
        Customer returnedCustomer = this.customerRepository.findById(createRentHistoryRequest.getCustomerId()).get();
        Car returnedCar = this.carRepository.findById(createRentHistoryRequest.getCarId()).get();
        RentHistory rentHistory = this.modelMapperService
                .forRequest()
                .typeMap(CreateRentHistoryRequest.class, RentHistory.class)
                .addMappings(mapper -> mapper.skip(RentHistory::setId))
                .map(createRentHistoryRequest);

        rentHistory.setCar(returnedCar);
        rentHistory.setCustomer(returnedCustomer);

        this.rentHistoryRepository.save(rentHistory);
    }

    @Override
    public List<GetAllRentHistoryResponse> getAllByRentHistories(List<RentHistory> rentHistories) {
        List<GetAllRentHistoryResponse> rentHistoryResponses = new ArrayList<>();
        for (RentHistory rentHistory:rentHistories) {
            GetAllRentHistoryResponse rentHistoryResponse = this.modelMapperService
                    .forResponse()
                    .typeMap(RentHistory.class, GetAllRentHistoryResponse.class)
                    .addMappings(mapper -> mapper.skip(GetAllRentHistoryResponse::setCustomerFullName))
                    .map(rentHistory);
            rentHistoryResponse.setCustomerFullName(rentHistory.getCustomer().getName() + " " + rentHistory.getCustomer().getSurname());

            rentHistoryResponses.add(rentHistoryResponse);
        }
        return rentHistoryResponses;
    }

    @Override
    public List<GetAllRentHistoryResponse> getAll() {
        List<RentHistory> rentHistories = this.rentHistoryRepository.findAll();

        return this.getAllByRentHistories(rentHistories);
    }

    @Override
    public List<GetAllRentHistoryResponse> getAllByCustomer(Customer customer) {
        List<RentHistory> rentHistories = this.rentHistoryRepository.findAllByCustomer(customer);

        return this.getAllByRentHistories(rentHistories);
    }


}
