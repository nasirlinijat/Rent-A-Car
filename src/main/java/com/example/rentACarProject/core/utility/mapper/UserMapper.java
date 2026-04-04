package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateUserRequest;
import com.example.rentACarProject.dto.requests.update.UpdateUserRequest;
import com.example.rentACarProject.dto.responses.UserResponse;
import com.example.rentACarProject.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);

    User toEntity(CreateUserRequest request);

    User toEntity(UpdateUserRequest request);
}