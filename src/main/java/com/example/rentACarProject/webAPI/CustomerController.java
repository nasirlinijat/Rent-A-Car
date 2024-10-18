package com.example.rentACarProject.webAPI;

import com.example.rentACarProject.business.abstracts.CustomerService;
import com.example.rentACarProject.business.requests.create.CreateCustomerRequest;
import com.example.rentACarProject.business.requests.update.UpdateCustomerRequest;
import com.example.rentACarProject.business.responses.GetAllCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<GetAllCustomerResponse> getAll(){
        return this.customerService.getAll();
    }

    @PostMapping
    public void add(@RequestBody CreateCustomerRequest createCustomerRequest){
        this.customerService.add(createCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.customerService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        this.customerService.update(updateCustomerRequest);
    }
}
