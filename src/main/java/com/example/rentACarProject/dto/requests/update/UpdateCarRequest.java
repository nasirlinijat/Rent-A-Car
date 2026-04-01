package com.example.rentACarProject.dto.requests.update;


import com.example.rentACarProject.entity.CarState;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    @NotBlank(message = "Plate cannot be blank")
    @Size(min = 2, max = 20, message = "Plate must be between 2 and 20 characters")
    private String plate;

    @NotNull(message = "Model year cannot be null")
    private Integer modelYear;

    @NotNull(message = "Car state cannot be null")
    private CarState state;

    @NotNull(message = "Daily price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Daily price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Daily price format is invalid")
    private BigDecimal dailyPrice;

    @NotNull(message = "Model cannot be null")
    private Long modelId;

    private String imagePath;
}