package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateModelRequest;
import com.example.rentACarProject.dto.requests.update.UpdateModelRequest;
import com.example.rentACarProject.dto.responses.ModelResponse;

import java.util.List;

public interface ModelService {
    List<ModelResponse> getAll();
    ModelResponse getById(Long id);
    ModelResponse add(CreateModelRequest request);
    ModelResponse update(Long id, UpdateModelRequest request);
    void delete(Long id);
}