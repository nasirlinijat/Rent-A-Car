package com.example.rentACarProject.dataAccess.abstracts;

import com.example.rentACarProject.entities.concrates.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByPlate(String plate);
    boolean existsById(int id);
}
