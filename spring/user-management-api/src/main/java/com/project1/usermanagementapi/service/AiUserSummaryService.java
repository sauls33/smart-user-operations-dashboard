package com.project1.usermanagementapi.service;

import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AiUserSummaryService {

    private final UserRepository userRepository;

    public AiUserSummaryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateSummary() {
        List<User> users = userRepository.findAll();

        int total = users.size();

        if (total == 0) {
            return "No users are currently registered. Recommendation: onboard initial users before evaluating operational risk.";
        }

        long active = users.stream().filter(User::isActive).count();
        long inactive = total - active;

        long highRisk = users.stream()
                .filter(user -> "HIGH".equalsIgnoreCase(user.getRiskLevel()))
                .count();

        long staleUsers = users.stream()
                .filter(user -> user.getLastLoginDate() != null)
                .filter(user -> user.getLastLoginDate().isBefore(LocalDate.now().minusDays(30)))
                .count();

        Map<String, Long> inactiveByDepartment = users.stream()
                .filter(user -> !user.isActive())
                .collect(Collectors.groupingBy(User::getDepartment, Collectors.counting()));

        String mostAffectedDepartment = inactiveByDepartment.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No department");

        String recommendation = buildRecommendation(total, inactive, highRisk, staleUsers, mostAffectedDepartment);

        return String.format(
                "Smart User Operations Insight: There are %d users. %d are active and %d are inactive. %d users are marked as HIGH risk, and %d users have not logged in for more than 30 days. Most affected department: %s. Recommendation: %s",
                total,
                active,
                inactive,
                highRisk,
                staleUsers,
                mostAffectedDepartment,
                recommendation
        );
    }

    private String buildRecommendation(int total, long inactive, long highRisk, long staleUsers, String mostAffectedDepartment) {
        double inactiveRatio = (double) inactive / total;

        if (highRisk > 0 || staleUsers > 0) {
            return "prioritize a review of high-risk and stale accounts, starting with " + mostAffectedDepartment + ".";
        }

        if (inactiveRatio > 0.3) {
            return "review onboarding and engagement workflows because inactivity is above the expected threshold.";
        }

        return "the user base looks healthy. Continue monitoring activity and access risk weekly.";
    }
}