package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.ModelService;
import com.example.rentACarProject.business.rules.ModelBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.ModelEntityMapper;
import com.example.rentACarProject.dto.requests.create.CreateModelRequest;
import com.example.rentACarProject.dto.requests.update.UpdateModelRequest;
import com.example.rentACarProject.dto.responses.ModelResponse;
import com.example.rentACarProject.repository.BrandRepository;
import com.example.rentACarProject.repository.ModelRepository;
import com.example.rentACarProject.entity.Brand;
import com.example.rentACarProject.entity.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelEntityMapper modelEntityMapper;
    private final ModelBusinessRules modelBusinessRules;

    @Override
    public List<ModelResponse> getAll() {
        return modelRepository.findAll()
                .stream()
                .map(modelEntityMapper::toResponse)
                .toList();
    }

    @Override
    public ModelResponse getById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Model with id " + id + " does not exist"));
        return modelEntityMapper.toResponse(model);
    }

    @Override
    public ModelResponse add(CreateModelRequest request) {
        modelBusinessRules.checkIfModelNameExists(request.getName());
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new BusinessException("Brand with id " + request.getBrandId() + " does not exist"));
        Model model = modelEntityMapper.toEntity(request);
        model.setBrand(brand);
        Model saved = modelRepository.save(model);
        return modelEntityMapper.toResponse(saved);
    }

    @Override
    public ModelResponse update(Long id, UpdateModelRequest request) {
        modelBusinessRules.checkIfModelNameExistsForOtherModel(request.getName(), id);
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Model with id " + id + " does not exist"));
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new BusinessException("Brand with id " + request.getBrandId() + " does not exist"));
        model.setName(request.getName());
        model.setBrand(brand);
        Model saved = modelRepository.save(model);
        return modelEntityMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Model with id " + id + " does not exist"));
        modelRepository.delete(model);
    }
}