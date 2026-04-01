package com.example.rentACarProject.dto.requests.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentHistoryRequest {

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate rentalStartDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    private LocalDate rentalEndDate;

    @NotBlank(message = "Pickup location cannot be blank")
    @Size(max = 255, message = "Pickup location is too long")
    private String pickupLocation;

    @NotBlank(message = "Return location cannot be blank")
    @Size(max = 255, message = "Return location is too long")
    private String returnLocation;

    @NotNull(message = "Car id cannot be null")
    private Long carId;

    @NotNull(message = "Customer id cannot be null")
    private Long customerId;
}
