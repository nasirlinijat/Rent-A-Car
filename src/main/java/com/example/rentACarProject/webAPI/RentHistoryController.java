package com.example.rentACarProject.webAPI;

import com.example.rentACarProject.business.abstracts.RentHistoryService;
import com.example.rentACarProject.business.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.business.responses.GetAllRentHistoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/rentHistories")
public class RentHistoryController {
    private final RentHistoryService rentHistoryService;

    @GetMapping
    public List<GetAllRentHistoryResponse> getAll(){
        return this.rentHistoryService.getAll();
    }

    @PostMapping
    public void add(@RequestBody CreateRentHistoryRequest createRentHistoryRequest){
        this.rentHistoryService.add(createRentHistoryRequest);
    }
}
