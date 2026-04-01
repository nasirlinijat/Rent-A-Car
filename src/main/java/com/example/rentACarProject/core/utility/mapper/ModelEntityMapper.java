package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateModelRequest;
import com.example.rentACarProject.dto.requests.update.UpdateModelRequest;
import com.example.rentACarProject.dto.responses.ModelResponse;
import com.example.rentACarProject.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "brand.name", target = "brandName")
    ModelResponse toResponse(Model model);

    Model toEntity(CreateModelRequest request);

    Model toEntity(UpdateModelRequest request);
}