package com.example.rentACarProject.core.utilities.mapper.abstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
