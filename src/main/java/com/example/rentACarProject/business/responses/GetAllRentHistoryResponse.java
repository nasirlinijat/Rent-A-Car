package com.example.rentACarProject.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentHistoryResponse {
    private int id;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private BigDecimal totalCost;
    private String status;
    private String pickupLocation;
    private String returnLocation;
    private int carId;
    private String carName;
    private String brandName;
    private int customerId;
    private String customerFullName;
}
