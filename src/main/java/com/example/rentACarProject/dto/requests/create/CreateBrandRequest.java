package com.example.rentACarProject.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

    @NotBlank(message = "Brand name cannot be blank")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;
}