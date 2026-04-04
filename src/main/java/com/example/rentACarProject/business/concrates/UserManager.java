package com.example.rentACarProject.business.concrates;

import com.example.rentACarProject.business.abstracts.UserService;
import com.example.rentACarProject.business.rules.UserBusinessRules;
import com.example.rentACarProject.core.utility.exceptions.BusinessException;
import com.example.rentACarProject.core.utility.mapper.UserMapper;
import com.example.rentACarProject.dto.requests.create.CreateUserRequest;
import com.example.rentACarProject.dto.requests.update.UpdateUserRequest;
import com.example.rentACarProject.dto.responses.UserResponse;
import com.example.rentACarProject.entity.Role;
import com.example.rentACarProject.entity.User;
import com.example.rentACarProject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User with id " + id + " does not exist"));

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse add(CreateUserRequest request) {
        userBusinessRules.checkIfEmailExists(request.getEmail());
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        userBusinessRules.checkIfUserExists(id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User with id " + id + " does not exist"));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User with id " + id + " does not exist"));
        userRepository.delete(user);
    }
}