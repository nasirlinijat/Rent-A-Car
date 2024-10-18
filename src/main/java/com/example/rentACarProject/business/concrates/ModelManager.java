package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.BrandService;
import com.example.rentACarProject.business.abstracts.ModelService;
import com.example.rentACarProject.business.requests.create.CreateModelRequest;
import com.example.rentACarProject.business.requests.update.UpdateModelRequest;
import com.example.rentACarProject.business.responses.GetAllModelResponse;
import com.example.rentACarProject.business.rules.ModelBusinessRules;
import com.example.rentACarProject.core.utilities.mapper.abstracts.ModelMapperService;
import com.example.rentACarProject.dataAccess.abstracts.ModelRepository;
import com.example.rentACarProject.entities.concrates.Brand;
import com.example.rentACarProject.entities.concrates.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;

    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = this.modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = new ArrayList<>();
        for (Model model: models){
            GetAllModelResponse modelResponse = this.modelMapperService
                    .forResponse()
                    .map(model, GetAllModelResponse.class);

            modelResponses.add(modelResponse);
        }
        return modelResponses;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        this.modelBusinessRules.checkIfModelNameExist(createModelRequest.getName());
        Brand returnedBrand = this.brandService.getBrandById(createModelRequest.getBrandId());
        Model model = this.modelMapperService
                .forRequest()
                .typeMap(CreateModelRequest.class, Model.class)
                .addMappings(mapper->mapper.skip(Model::setId))
                .map(createModelRequest);

        model.setBrand(returnedBrand);

        this.modelRepository.save(model);
    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        this.modelBusinessRules.checkIfModelNameExist(updateModelRequest.getName());
        Brand returnedBrand = this.brandService.getBrandById(updateModelRequest.getBrandId());
        Model model = this.modelMapperService
                .forRequest()
                .map(updateModelRequest, Model.class);

        model.setBrand(returnedBrand);

        this.modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        this.modelRepository.findById(id).ifPresent(this.modelRepository::delete);
    }

    @Override
    public Model getModelById(int id) {
        return this.modelRepository.findById(id).get();
    }

}
