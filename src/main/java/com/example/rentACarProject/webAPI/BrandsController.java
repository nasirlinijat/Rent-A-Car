package com.example.rentACarProject.webAPI;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.business.requests.create.CreateBrandRequest;
import com.example.rentACarProject.business.requests.update.UpdateBrandRequest;
import com.example.rentACarProject.business.responses.GetAllBrandResponse;
import com.example.rentACarProject.business.responses.GetByIdBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandsController {
    private final BrandService brandService;

    @GetMapping
    public List<GetAllBrandResponse> getAllBrands() {

        return this.brandService.getAll();
    }


    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable int id){

        return this.brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest){

        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){

        this.brandService.delete(id);
    }

}
