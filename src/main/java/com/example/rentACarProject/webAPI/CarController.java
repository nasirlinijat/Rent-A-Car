package com.example.rentACarProject.webAPI;

import com.example.rentACarProject.business.concrates.CarManager;
import com.example.rentACarProject.business.requests.create.CreateCarRequest;
import com.example.rentACarProject.business.requests.update.UpdateCarRequest;
import com.example.rentACarProject.business.responses.GetAllCarResponse;
import com.example.rentACarProject.business.responses.GetByIdCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private final CarManager carManager;

    @GetMapping
    public List<GetAllCarResponse> getAll(){
        return this.carManager.getAll();
    }

    @PostMapping
    public void add(@RequestBody CreateCarRequest createCarRequest){
        this.carManager.add(createCarRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateCarRequest updateCarRequest){
        this.carManager.update(updateCarRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.carManager.delete(id);
    }

    @GetMapping("/{id}")
    public GetByIdCarResponse getById(@PathVariable int id){
        return this.carManager.getById(id);
    }
}
