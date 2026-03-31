package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.CarService;
import com.example.rentACarProject.business.rules.CarBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.CarMapper;
import com.example.rentACarProject.dto.requests.create.CreateCarRequest;
import com.example.rentACarProject.dto.requests.update.UpdateCarRequest;
import com.example.rentACarProject.dto.responses.CarResponse;
import com.example.rentACarProject.repository.CarRepository;
import com.example.rentACarProject.entity.Car;
import com.example.rentACarProject.entity.Model;
import com.example.rentACarProject.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final CarMapper carMapper;
    private final CarBusinessRules carBusinessRules;

    @Override
    public List<CarResponse> getAll() {

        return carRepository.findAll()
                .stream()
                .map(carMapper::toResponse)
                .toList();
    }

    @Override
    public CarResponse getById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Car with id " + id + " does not exist"));

        return carMapper.toResponse(car);
    }

    @Override
    public CarResponse add(CreateCarRequest request) {
        carBusinessRules.checkIfPlateExists(request.getPlate().toUpperCase());
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new BusinessException("Model with id " + request.getModelId() + " does not exist"));
        Car car = carMapper.toEntity(request);
        car.setPlate(request.getPlate().toUpperCase());
        car.setModel(model);
        Car saved = carRepository.save(car);

        return carMapper.toResponse(saved);
    }

    @Override
    public CarResponse update(Long id, UpdateCarRequest request) {
        carBusinessRules.checkIfPlateExistsForOtherCar(request.getPlate().toUpperCase(), id);
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Car with id " + id + " does not exist"));
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new BusinessException("Model with id " + request.getModelId() + " does not exist"));
        car.setPlate(request.getPlate().toUpperCase());
        car.setModelYear(request.getModelYear());
        car.setState(request.getState());
        car.setDailyPrice(request.getDailyPrice());
        car.setImagePath(request.getImagePath());
        car.setModel(model);
        Car saved = carRepository.save(car);

        return carMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Car with id " + id + " does not exist"));
        carRepository.delete(car);
    }
}