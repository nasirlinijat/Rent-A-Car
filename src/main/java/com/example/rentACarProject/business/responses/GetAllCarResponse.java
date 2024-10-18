package com.example.rentACarProject.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarResponse {
    private int id;
    private String plate;
    private int modelYear;
    private int state;
    private double dailyPrice;
    private String imagePath;
    private String brandName;
    private String modelName;
}
