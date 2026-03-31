package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.entity.RentStatus;
import com.example.rentACarProject.repository.CarRepository;
import com.example.rentACarProject.repository.RentHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RentHistoryBusinessRules {

    private final CarRepository carRepository;
    private final RentHistoryRepository rentHistoryRepository;

    public void checkIfCarExists(Long id) {
        if (!carRepository.existsById(id)) {
            throw new BusinessException("Car with id " + id + " does not exist");
        }
    }

    public void checkIfCarIsAvailable(Long carId) {
        if (rentHistoryRepository.existsByCarIdAndStatus(carId, RentStatus.ACTIVE)) {
            throw new BusinessException("Car with id " + carId + " is already rented");
        }
    }
}