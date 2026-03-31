package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BrandBusinessRules {

    private final BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name) {
        if (brandRepository.existsByName(name)) {
            throw new BusinessException("Brand with name '" + name + "' already exists");
        }
    }

    public void checkIfBrandIdExists(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new BusinessException("Brand with id " + id + " does not exist");
        }
    }
}
