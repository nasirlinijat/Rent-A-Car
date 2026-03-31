package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.dto.requests.create.CreateBrandRequest;
import com.example.rentACarProject.dto.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.dto.responses.BrandResponse;

import java.util.List;


public interface BrandService {
    List<BrandResponse> getAll();
    BrandResponse getById(Long id);
    BrandResponse add(CreateBrandRequest request);
    BrandResponse update(Long id, UpdateBrandRequest request);
    void delete(Long id);
}
