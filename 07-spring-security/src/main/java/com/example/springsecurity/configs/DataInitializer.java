package com.example.springsecurity.configs;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User employee = User.builder()
                        .username("edona")
                        .password(passwordEncoder.encode("edona123"))
                        .role(Role.EMPLOYEE)
                        .build();

                User manager = User.builder()
                        .username("almir")
                        .password(passwordEncoder.encode("almir123"))
                        .role(Role.MANAGER)
                        .build();

                User admin = User.builder()
                        .username("eris")
                        .password(passwordEncoder.encode("eris123"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.saveAll(List.of(employee, manager, admin));
            }
        };
    }
}