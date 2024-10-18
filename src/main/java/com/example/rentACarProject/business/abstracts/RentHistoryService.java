package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.business.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.business.responses.GetAllRentHistoryResponse;
import com.example.rentACarProject.entities.concrates.Customer;
import com.example.rentACarProject.entities.concrates.RentHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentHistoryService {
    void add(CreateRentHistoryRequest createRentHistoryRequest);
    List<GetAllRentHistoryResponse> getAll();
    List<GetAllRentHistoryResponse> getAllByCustomer(Customer customer);
    List<GetAllRentHistoryResponse> getAllByRentHistories(List<RentHistory> rentHistories);
}
