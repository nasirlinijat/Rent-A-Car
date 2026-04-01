package com.example.rentACarProject.dto.requests.update;

import com.example.rentACarProject.entity.RentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentHistoryRequest {

    @NotNull(message = "Status cannot be null")
    private RentStatus status;
}
