package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateCarRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCarRequest;
import com.example.rentACarProject.dto.responses.CarResponse;
import com.example.rentACarProject.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "model.name", target = "modelName")
    @Mapping(source = "model.brand.name", target = "brandName")
    CarResponse toResponse(Car car);

    Car toEntity(CreateCarRequest request);

    Car toEntity(UpdateCarRequest request);
}