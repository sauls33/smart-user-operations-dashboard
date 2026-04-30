package com.project1.usermanagementapi.config;

import com.project1.usermanagementapi.entity.User;
import com.project1.usermanagementapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User(null, "Ana Torres", "ana@example.com", true));
                userRepository.save(new User(null, "Luis Garcia", "luis@example.com", false));
                userRepository.save(new User(null, "Maya Rivera", "maya@example.com", true));
            }
        };
    }
}
