package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateCarRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCarRequest;
import com.example.rentACarProject.dto.responses.CarResponse;

import java.util.List;

public interface CarService {
    List<CarResponse> getAll();
    CarResponse getById(Long id);
    CarResponse add(CreateCarRequest request);
    CarResponse update(Long id, UpdateCarRequest request);
    void delete(Long id);
}