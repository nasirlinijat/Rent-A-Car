package com.example.rentACarProject.controller;

import com.example.rentACarProject.business.abstracts.CustomerService;
import com.example.rentACarProject.dto.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.dto.responses.CustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {

        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> add(@RequestBody @Valid CreateCustomerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.add(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateCustomerRequest request) {

        return ResponseEntity.ok(customerService.update(id, request));
    }
}
