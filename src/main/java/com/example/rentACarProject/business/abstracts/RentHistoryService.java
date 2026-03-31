package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;

import java.util.List;

public interface RentHistoryService {
    List<RentHistoryResponse> getAll();
    RentHistoryResponse getById(Long id);
    List<RentHistoryResponse> getAllByCustomerId(Long customerId);
    List<RentHistoryResponse> getAllByCarId(Long carId);
    RentHistoryResponse add(CreateRentHistoryRequest request);
    void delete(Long id);
}