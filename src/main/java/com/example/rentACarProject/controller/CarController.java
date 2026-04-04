package com.example.rentACarProject.controller;

import com.example.rentACarProject.business.abstracts.CarService;
import com.example.rentACarProject.dto.requests.create.CreateCarRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCarRequest;
import com.example.rentACarProject.dto.responses.CarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cars")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CarController {

    private final CarService carService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<List<CarResponse>> getAll() {

        return ResponseEntity.ok(carService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<CarResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(carService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CarResponse> add(@RequestBody @Valid CreateCarRequest createCarRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carService.add(createCarRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> update(@PathVariable Long id,
                                              @RequestBody @Valid UpdateCarRequest updateCarRequest) {

        return ResponseEntity.ok(carService.update(id, updateCarRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.carService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
