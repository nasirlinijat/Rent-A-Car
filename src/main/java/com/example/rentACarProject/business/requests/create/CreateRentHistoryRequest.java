package com.example.rentACarProject.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentHistoryRequest {
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private BigDecimal totalCost;
    private String status;
    private String pickupLocation;
    private String returnLocation;
    private int carId;
    private int customerId;
}
