package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.business.requests.create.CreateModelRequest;
import com.example.rentACarProject.business.requests.update.UpdateModelRequest;
import com.example.rentACarProject.business.responses.GetAllModelResponse;
import com.example.rentACarProject.entities.concrates.Model;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    Model getModelById(int id);
    void add (CreateModelRequest createModelRequest);
    void update(UpdateModelRequest updateModelRequest);
    void delete(int id);
}
