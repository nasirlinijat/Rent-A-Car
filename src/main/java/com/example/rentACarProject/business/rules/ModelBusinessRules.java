package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ModelBusinessRules {

    private final ModelRepository modelRepository;

    public void checkIfModelNameExists(String name) {
        if (modelRepository.existsByName(name)) {
            throw new BusinessException("Model with name '" + name + "' already exists");
        }
    }

    public void checkIfModelExists(Long id) {
        if (!modelRepository.existsById(id)) {
            throw new BusinessException("Model with id " + id + " does not exist");
        }
    }
    public void checkIfModelNameExistsForOtherModel(String name, Long id){
        if (modelRepository.existsByNameAndIdNot(name, id)){
            throw new BusinessException("Model with name '" + name + "' already exists");
        }
    }
}