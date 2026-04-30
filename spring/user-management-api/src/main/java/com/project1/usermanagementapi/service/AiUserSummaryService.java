package com.project1.usermanagementapi.service;

import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiUserSummaryService {

    private final UserRepository userRepository;

    public AiUserSummaryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateSummary() {
        List<User> users = userRepository.findAll();

        int total = users.size();
        long active = users.stream().filter(User::isActive).count();
        long inactive = total - active;

        if (total == 0) {
            return "No users available in the system.";
        }

        double inactiveRatio = (double) inactive / total;

        String recommendation;

        if (inactiveRatio > 0.5) {
            recommendation = "High number of inactive users. Consider improving onboarding or engagement strategies.";
        } else if (inactiveRatio > 0.2) {
            recommendation = "Moderate inactive users. Monitor engagement closely.";
        } else {
            recommendation = "User base is healthy.";
        }

        return String.format(
                "There are %d users. %d are active and %d are inactive. Inactive ratio: %.2f%%. %s",
                total,
                active,
                inactive,
                inactiveRatio * 100,
                recommendation
        );
    }
}