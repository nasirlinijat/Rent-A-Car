package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.business.abstracts.CarService;
import com.example.rentACarProject.business.abstracts.ModelService;
import com.example.rentACarProject.business.requests.create.CreateCarRequest;
import com.example.rentACarProject.business.requests.update.UpdateCarRequest;
import com.example.rentACarProject.business.responses.GetAllCarResponse;
import com.example.rentACarProject.business.responses.GetByIdCarResponse;
import com.example.rentACarProject.business.rules.CarBusinessRules;
import com.example.rentACarProject.core.utilities.mapper.abstracts.ModelMapperService;
import com.example.rentACarProject.dataAccess.abstracts.CarRepository;
import com.example.rentACarProject.entities.concrates.Brand;
import com.example.rentACarProject.entities.concrates.Car;
import com.example.rentACarProject.entities.concrates.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final ModelMapperService modelMapperService;
    private final CarRepository carRepository;
    private final BrandService brandService;
    private final ModelService modelService;
    private final CarBusinessRules carBusinessRules;

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = this.carRepository.findAll();
        List<GetAllCarResponse> carResponses = new ArrayList<>();
        for(Car car : cars){
            Model returnedModel = this.modelService.getModelById(car.getModel().getId());
            Brand returnedBrand = this.brandService.getBrandById(returnedModel.getBrand().getId());
            GetAllCarResponse carResponse = this.modelMapperService
                    .forResponse()
                    .map(car, GetAllCarResponse.class);

            carResponse.setBrandName(returnedBrand.getName());
            carResponse.setModelName(returnedModel.getName());

            carResponses.add(carResponse);
        }
        return carResponses;
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        this.carBusinessRules.checkIfPlateExist(createCarRequest.getPlate());
        Model returnedModel = this.modelService.getModelById(createCarRequest.getModelId());
        Car car = this.modelMapperService.
                forRequest()
                .typeMap(CreateCarRequest.class, Car.class)
                .addMappings(mapper->mapper.skip(Car::setId))
                .map(createCarRequest);

        car.setModel(returnedModel);

        this.carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        this.carBusinessRules.checkIfPlateExist(updateCarRequest.getPlate());
        Model returnedModel = this.modelService.getModelById(updateCarRequest.getModelId());
        Car car = this.modelMapperService
                .forRequest()
                .map(updateCarRequest, Car.class);

        car.setModel(returnedModel);

        this.carRepository.save(car);
    }

    @Override
    public GetByIdCarResponse getById(int id) {
        this.carBusinessRules.checkIfCarExists(id);

        Car returnedCar = this.carRepository.findById(id).orElseThrow();

        return this.modelMapperService
                .forResponse()
                .map(returnedCar, GetByIdCarResponse.class);
    }

    @Override
    public void delete(int id) {
        this.carRepository.findById(id).ifPresent(this.carRepository::delete);
    }
}
