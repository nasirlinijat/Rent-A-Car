package com.example.rentACarProject.dataAccess.abstracts;

import com.example.rentACarProject.entities.concrates.Customer;
import com.example.rentACarProject.entities.concrates.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Integer> {
    List<RentHistory> findAllByCustomer(Customer customer);
}
