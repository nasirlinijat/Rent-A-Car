package com.example.rentACarProject.controller;

import com.example.rentACarProject.business.abstracts.ModelService;
import com.example.rentACarProject.dto.requests.create.CreateModelRequest;
import com.example.rentACarProject.dto.requests.update.UpdateModelRequest;
import com.example.rentACarProject.dto.responses.ModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ModelsController {

    private final ModelService modelService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<List<ModelResponse>> getAll() {

        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ModelResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(modelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ModelResponse> add(@RequestBody @Valid CreateModelRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> update(@PathVariable Long id,
                                                @RequestBody @Valid UpdateModelRequest request) {

        return ResponseEntity.ok(modelService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.modelService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
