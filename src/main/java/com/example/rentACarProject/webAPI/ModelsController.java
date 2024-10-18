package com.example.rentACarProject.webAPI;

import com.example.rentACarProject.business.abstracts.ModelService;
import com.example.rentACarProject.business.requests.create.CreateModelRequest;
import com.example.rentACarProject.business.requests.update.UpdateModelRequest;
import com.example.rentACarProject.business.responses.GetAllModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelsController {
    private ModelService modelService;

    @GetMapping
    public List<GetAllModelResponse> getAll(){
        return this.modelService.getAll();
    }

    @PostMapping
    private ResponseEntity<String> add(@RequestBody CreateModelRequest createModelRequest){
        this.modelService.add(createModelRequest);

        return ResponseEntity.ok("Model is Valid");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UpdateModelRequest updateModelRequest){
        this.modelService.update(updateModelRequest);

        return ResponseEntity.ok("Model is Valid");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        this.modelService.delete(id);

        return ResponseEntity.notFound().build();
    }
}
