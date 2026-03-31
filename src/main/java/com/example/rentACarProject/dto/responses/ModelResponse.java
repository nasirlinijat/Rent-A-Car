package com.example.rentACarProject.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {
    private Long id;
    private String name;
    private Long brandId;
    private String brandName;
}
