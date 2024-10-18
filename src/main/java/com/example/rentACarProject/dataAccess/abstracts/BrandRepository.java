package com.example.rentACarProject.dataAccess.abstracts;

import com.example.rentACarProject.entities.concrates.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
    boolean existsById(int id);
    Brand getBrandById(int id);
}
