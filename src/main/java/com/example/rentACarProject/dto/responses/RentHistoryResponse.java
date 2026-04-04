package com.example.rentACarProject.dto.responses;

import com.example.rentACarProject.entity.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentHistoryResponse {
    private Long id;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private BigDecimal totalCost;
    private RentStatus status;
    private String pickupLocation;
    private String returnLocation;
    private Long carId;
    private String plate;
    private Long userId;
    private String userFullName;
}
