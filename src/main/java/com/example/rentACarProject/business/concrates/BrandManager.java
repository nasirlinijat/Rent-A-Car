package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.business.requests.create.CreateBrandRequest;
import com.example.rentACarProject.business.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.business.responses.GetAllBrandResponse;
import com.example.rentACarProject.business.responses.GetByIdBrandResponse;
import com.example.rentACarProject.business.rules.BrandBusinessRules;
import com.example.rentACarProject.core.utilities.mapper.abstracts.ModelMapperService;
import com.example.rentACarProject.dataAccess.abstracts.BrandRepository;
import com.example.rentACarProject.entities.concrates.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brand -> this.modelMapperService
                        .forResponse()
                        .map(brand, GetAllBrandResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        this.brandBusinessRules.checkIfBrandIdExists(id);
        Brand returnedBrand = this.brandRepository.findById(id).orElseThrow();

        return this.modelMapperService
                .forResponse()
                .map(returnedBrand, GetByIdBrandResponse.class);
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
        Brand brand = this.modelMapperService
                .forRequest()
                .map(createBrandRequest, Brand.class);

        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService
                .forRequest()
                .map(updateBrandRequest, Brand.class);

        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        this.brandRepository.findById(id).ifPresent(this.brandRepository::delete);
    }

    @Override
    public Brand getBrandById(int id) {
        return this.brandRepository.findById(id).get();
    }

}
