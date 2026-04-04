package com.example.rentACarProject.controller;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.dto.requests.create.CreateBrandRequest;
import com.example.rentACarProject.dto.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.dto.responses.BrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BrandResponse> add(@RequestBody @Valid CreateBrandRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable Long id,
                                                @RequestBody @Valid UpdateBrandRequest request) {
        return ResponseEntity.ok(brandService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
