package com.example.rentACarProject.repository;

import com.example.rentACarProject.entity.RentHistory;
import com.example.rentACarProject.entity.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {
    List<RentHistory> findAllByUserId(Long userId);
    List<RentHistory> findAllByCarId(Long carId);
    boolean existsByCarIdAndStatus(Long id, RentStatus status);
}
