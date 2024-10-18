package com.example.rentACarProject.business.responses;

import com.example.rentACarProject.entities.concrates.RentHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomerResponse {
    private int id;
    private String name;
    private String surname;
    private String email;
    private List<GetAllRentHistoryResponse> rentHistories;
}
