package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.RentHistoryService;
import com.example.rentACarProject.business.rules.RentHistoryBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.RentHistoryMapper;
import com.example.rentACarProject.dto.requests.create.CreateRentHistoryRequest;
import com.example.rentACarProject.dto.requests.update.UpdateRentHistoryRequest;
import com.example.rentACarProject.dto.responses.RentHistoryResponse;
import com.example.rentACarProject.entity.RentStatus;
import com.example.rentACarProject.repository.CarRepository;
import com.example.rentACarProject.repository.UserRepository;
import com.example.rentACarProject.repository.RentHistoryRepository;
import com.example.rentACarProject.entity.Car;
import com.example.rentACarProject.entity.User;
import com.example.rentACarProject.entity.RentHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class RentHistoryManager implements RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RentHistoryMapper rentHistoryMapper;
    private final RentHistoryBusinessRules rentHistoryBusinessRules;

    @Override
    public List<RentHistoryResponse> getAll() {
        return rentHistoryRepository.findAll()
                .stream()
                .map(rentHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public RentHistoryResponse getById(Long id) {
        RentHistory rentHistory = rentHistoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("RentHistory with id " + id + " does not exist"));
        return rentHistoryMapper.toResponse(rentHistory);
    }

    @Override
    public List<RentHistoryResponse> getAllByUserId(Long userId) {
        return rentHistoryRepository.findAllByUserId(userId).stream()
                .map(rentHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<RentHistoryResponse> getAllByCustomerId(Long customerId) {
        return rentHistoryRepository.findAllByUserId(customerId)
                .stream()
                .map(rentHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<RentHistoryResponse> getAllByCarId(Long carId) {
        return rentHistoryRepository.findAllByCarId(carId)
                .stream()
                .map(rentHistoryMapper::toResponse)
                .toList();
    }


    @Override
    public RentHistoryResponse add(CreateRentHistoryRequest request) {
        rentHistoryBusinessRules.checkIfCarExists(request.getCarId());
        rentHistoryBusinessRules.checkIfCarIsAvailable(request.getCarId());

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new BusinessException("Car with id " + request.getCarId() + " does not exist"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException("User with id " + request.getUserId() + " does not exist"));

        long days = ChronoUnit.DAYS.between(request.getRentalStartDate(), request.getRentalEndDate());
        BigDecimal totalCost = car.getDailyPrice().multiply(BigDecimal.valueOf(days));

        RentHistory rentHistory = rentHistoryMapper.toEntity(request);
        rentHistory.setCar(car);
        rentHistory.setUser(user);
        rentHistory.setTotalCost(totalCost);
        rentHistory.setStatus(RentStatus.PENDING);

        RentHistory saved = rentHistoryRepository.save(rentHistory);

        return rentHistoryMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        RentHistory rentHistory = rentHistoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("RentHistory with id " + id + " does not exist"));
        rentHistoryRepository.delete(rentHistory);
    }

    @Override
    public RentHistoryResponse update(Long id, UpdateRentHistoryRequest request) {
        RentHistory rentHistory = rentHistoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("RentHistory with id " + id + " does not exist"));

        rentHistory.setStatus(request.getStatus());
        RentHistory saved = rentHistoryRepository.save(rentHistory);

        return rentHistoryMapper.toResponse(saved);
    }
}
