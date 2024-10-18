package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utilities.exceptions.BusinessException;
import com.example.rentACarProject.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name){
        if (this.brandRepository.existsByName(name)){
            throw new BusinessException("Brand name already exists!");
        }
    }

    public void checkIfBrandIdExists(int id){
        if (!this.brandRepository.existsById(id)){
            throw new BusinessException("Requested id do not exists!");
        }
    }
}
