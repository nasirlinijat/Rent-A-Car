package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;
import com.example.rentACarProject.entity.RentHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentHistoryMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "car.plate", target = "plate")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "userFullName", expression = "java(rentHistory.getUser().getName() + \" \" + rentHistory.getUser().getSurname())")
    RentHistoryResponse toResponse(RentHistory rentHistory);

    RentHistory toEntity(CreateRentHistoryRequest request);
}