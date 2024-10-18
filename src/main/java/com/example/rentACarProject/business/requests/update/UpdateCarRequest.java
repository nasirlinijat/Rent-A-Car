package com.example.rentACarProject.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int id;
    private String plate;
    private int modelYear;
    private int state;
    private double dailyPrice;
    private int modelId;
    private String imagePath;

}
