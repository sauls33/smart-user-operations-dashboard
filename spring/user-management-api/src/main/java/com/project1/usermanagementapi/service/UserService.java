package com.project1.usermanagementapi.service;

import com.project1.usermanagementapi.dto.CreateUserRequest;
import com.project1.usermanagementapi.dto.UpdateUserRequest;
import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("A user with this email already exists.");
        }

        User user = new User();
        applyCreateRequest(user, request);

        return userRepository.save(user);
    }

    public User update(Long id, UpdateUserRequest request) {
        User existingUser = findById(id);

        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setActive(request.isActive());
        existingUser.setRole(request.getRole());
        existingUser.setDepartment(request.getDepartment());
        existingUser.setLastLoginDate(request.getLastLoginDate());
        existingUser.setRiskLevel(request.getRiskLevel());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        User existingUser = findById(id);
        userRepository.delete(existingUser);
    }

    private void applyCreateRequest(User user, CreateUserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setActive(request.isActive());
        user.setRole(request.getRole());
        user.setDepartment(request.getDepartment());
        user.setLastLoginDate(request.getLastLoginDate());
        user.setRiskLevel(request.getRiskLevel());
    }
}