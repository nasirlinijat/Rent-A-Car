package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.business.requests.create.CreateCarRequest;
import com.example.rentACarProject.business.requests.update.UpdateCarRequest;
import com.example.rentACarProject.business.responses.GetAllCarResponse;
import com.example.rentACarProject.business.responses.GetByIdCarResponse;

import java.util.List;


public interface CarService {
    List<GetAllCarResponse> getAll();
    void add(CreateCarRequest createCarRequest);
    void update(UpdateCarRequest updateCarRequest);
    GetByIdCarResponse getById(int id);
    void delete(int id);
}
