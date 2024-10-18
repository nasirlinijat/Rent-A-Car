package com.example.rentACarProject.business.abstracts;

import com.example.rentACarProject.business.requests.create.CreateBrandRequest;
import com.example.rentACarProject.business.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.business.responses.GetAllBrandResponse;
import com.example.rentACarProject.business.responses.GetByIdBrandResponse;
import com.example.rentACarProject.entities.concrates.Brand;

import java.util.List;


public interface BrandService {
    List<GetAllBrandResponse> getAll();
    GetByIdBrandResponse getById(int id);
    Brand getBrandById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);

}
