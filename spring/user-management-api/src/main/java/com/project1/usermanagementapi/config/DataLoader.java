package com.project1.usermanagementapi.config;

import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User ana = new User();
                ana.setName("Ana Torres");
                ana.setEmail("ana@example.com");
                ana.setActive(true);
                ana.setRole("Admin");
                ana.setDepartment("Operations");
                ana.setLastLoginDate(LocalDate.now().minusDays(2));
                ana.setRiskLevel("LOW");

                User luis = new User();
                luis.setName("Luis Garcia");
                luis.setEmail("luis@example.com");
                luis.setActive(false);
                luis.setRole("Analyst");
                luis.setDepartment("Finance");
                luis.setLastLoginDate(LocalDate.now().minusDays(45));
                luis.setRiskLevel("HIGH");

                User maria = new User();
                maria.setName("Maria Lopez");
                maria.setEmail("maria@example.com");
                maria.setActive(true);
                maria.setRole("Manager");
                maria.setDepartment("Engineering");
                maria.setLastLoginDate(LocalDate.now().minusDays(8));
                maria.setRiskLevel("MEDIUM");

                userRepository.save(ana);
                userRepository.save(luis);
                userRepository.save(maria);
            }
        };
    }
}