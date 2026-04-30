package com.project1.usermanagementapi.service;

import com.project1.usermanagementapi.dto.CreateUserRequest;
import com.project1.usermanagementapi.dto.UpdateUserRequest;
import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.exception.DuplicateEmailException;
import com.project1.usermanagementapi.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("A user with this email already exists");
        }

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setActive(request.isActive());
        return userRepository.save(user);
    }

    public User update(Long id, UpdateUserRequest request) {
        User existingUser = findById(id);
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmailAndIdNot(normalizedEmail, id)) {
            throw new DuplicateEmailException("A user with this email already exists");
        }

        existingUser.setName(request.getName().trim());
        existingUser.setEmail(normalizedEmail);
        existingUser.setActive(request.isActive());
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        User existingUser = findById(id);
        userRepository.delete(existingUser);
    }
}
