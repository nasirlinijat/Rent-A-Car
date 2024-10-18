package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utilities.exceptions.BusinessException;
import com.example.rentACarProject.dataAccess.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private final CarRepository carRepository;

    public void checkIfPlateExist(String plate) {
        if (this.carRepository.existsByPlate(plate.toUpperCase())) {
            throw new BusinessException("Car plate already exists!");
        }
    }

    public void checkIfCarExists(int id) {
        if (!this.carRepository.existsById(id)) {
            throw new BusinessException("Requested id do not exists!");
        }
    }
}
