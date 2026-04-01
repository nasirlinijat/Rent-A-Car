package com.example.rentACarProject.core.utility.mapper;

import com.example.rentACarProject.dto.requests.create.CreateBrandRequest;
import com.example.rentACarProject.dto.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.dto.responses.BrandResponse;
import com.example.rentACarProject.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toResponse(Brand brand);

    Brand toEntity(CreateBrandRequest request);

    Brand toEntity(UpdateBrandRequest request);
}