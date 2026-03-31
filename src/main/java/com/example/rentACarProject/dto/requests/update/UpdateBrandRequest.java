package com.example.rentACarProject.dto.requests.update;

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
public class UpdateBrandRequest {

    @NotBlank(message = "Brand name cannot be blank")
    @Size(min = 2, max = 20, message = "Brand name must be between 2 and 20 characters")
    private String name;
}