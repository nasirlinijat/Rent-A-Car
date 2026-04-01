package com.example.rentACarProject.controller;

import com.example.rentACarProject.business.abstracts.RentHistoryService;
import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.requests.update.UpdateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rentHistories")
@AllArgsConstructor
public class RentHistoryController {
    private final RentHistoryService rentHistoryService;

    @GetMapping
    public ResponseEntity<List<RentHistoryResponse>> getAll() {

        return ResponseEntity.ok(rentHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentHistoryResponse> getById(@PathVariable Long id){

        return ResponseEntity.ok(rentHistoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RentHistoryResponse> add(@RequestBody @Valid CreateRentHistoryRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.rentHistoryService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentHistoryResponse> update(@PathVariable Long id,
                                                      @RequestBody @Valid UpdateRentHistoryRequest request){

        return ResponseEntity.ok(rentHistoryService.update(id, request));
    }

}
