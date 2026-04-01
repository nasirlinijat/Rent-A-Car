package com.example.rentACarProject.dto.responses;

import com.example.rentACarProject.entity.CarState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private Long id;
    private String plate;
    private int modelYear;
    private CarState state;
    private BigDecimal dailyPrice;
    private String imagePath;
    private Long modelId;
    private String modelName;
    private String brandName;
}