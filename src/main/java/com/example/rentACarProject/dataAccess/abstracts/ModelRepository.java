package com.example.rentACarProject.dataAccess.abstracts;

import com.example.rentACarProject.entities.concrates.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    Model getModelById(int id);
    boolean existsByName(String name);
}
