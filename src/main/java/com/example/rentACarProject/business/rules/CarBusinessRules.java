package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CarBusinessRules {

    private final CarRepository carRepository;

    public void checkIfPlateExists(String plate) {
        if (carRepository.existsByPlate(plate)) {
            throw new BusinessException("Car with plate '" + plate + "' already exists");
        }
    }

    public void checkIfPlateExistsForOtherCar(String plate, Long id) {
        if (carRepository.existsByPlateAndIdNot(plate, id)) {
            throw new BusinessException("Car with plate '" + plate + "' already exists");
        }
    }

    public void checkIfCarExists(Long id) {
        if (!carRepository.existsById(id)) {
            throw new BusinessException("Car with id " + id + " does not exist");
        }
    }
}