package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.requests.update.UpdateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface RentHistoryService {
    List<RentHistoryResponse> getAll();

    RentHistoryResponse getById(Long id);

    List<RentHistoryResponse> getAllByUserId(Long userId);

    List<RentHistoryResponse> getAllByCustomerId(Long customerId);

    List<RentHistoryResponse> getAllByCarId(Long carId);

    RentHistoryResponse add(CreateRentHistoryRequest request);

    void delete(Long id);

    RentHistoryResponse update(Long id, UpdateRentHistoryRequest request);
}