package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.business.rules.BrandBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.BrandMapper;
import com.example.rentACarProject.dto.requests.create.CreateBrandRequest;
import com.example.rentACarProject.dto.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.dto.responses.BrandResponse;
import com.example.rentACarProject.repository.BrandRepository;
import com.example.rentACarProject.entity.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public List<BrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brandMapper::toResponse)
                .toList();
    }


    @Override
    public BrandResponse getById(Long id) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Brand with id " + id + " does not exist"));

        return brandMapper.toResponse(brand);
    }

    @Override
    public BrandResponse add(CreateBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExists(request.getName());
        Brand brand = brandMapper.toEntity(request);
        Brand saved = brandRepository.save(brand);

        return brandMapper.toResponse(saved);
    }

    @Override
    public BrandResponse update(Long id, UpdateBrandRequest request) {
        brandBusinessRules.checkIfBrandIdExists(id);
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Brand with id " + id + " does not exist"));
        Brand saved = this.brandRepository.save(brand);

        return brandMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Brand with id " + id + " does not exist"));

        brandRepository.delete(brand);
    }


}
