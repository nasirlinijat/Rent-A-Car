package com.example.rentACarProject.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private String plate;
    private int modelYear;
    private int state;
    private double dailyPrice;
    private int modelId;
    private String imagePath;
}
