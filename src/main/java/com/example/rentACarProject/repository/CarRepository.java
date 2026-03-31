package com.example.rentACarProject.repository;

import com.example.rentACarProject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByPlate(String plate);
    boolean existsByPlateAndIdNot(String plate, Long id);
}
