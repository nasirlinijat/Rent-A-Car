package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateUserRequest;
import com.example.rentACarProject.dto.requests.update.UpdateUserRequest;
import com.example.rentACarProject.dto.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    UserResponse add(CreateUserRequest request);
    UserResponse update(Long id, UpdateUserRequest request);
    void delete(Long id);
}