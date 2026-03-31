package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;
import com.example.rentACarProject.entity.RentHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentHistoryMapper {

    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.surname", target = "customerSurname")
    @Mapping(source = "car.plate", target = "plate")
    RentHistoryResponse toResponse(RentHistory rentHistory);

    RentHistory toEntity(CreateRentHistoryRequest request);
}