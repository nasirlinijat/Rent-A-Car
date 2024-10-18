package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utilities.exceptions.BusinessException;
import com.example.rentACarProject.dataAccess.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository modelRepository;

    public void checkIfModelNameExist(String name){
        if(this.modelRepository.existsByName(name)){
            throw new BusinessException("Model name already exists!");
        }
    }
}
