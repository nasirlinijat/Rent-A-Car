package com.example.rentACarProject.business.rules;

import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("User with email '" + email + "' already exists");
        }
    }

    public void checkIfUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("User with id " + id + " does not exist");
        }
    }
}
