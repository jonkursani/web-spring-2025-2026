package com.example.springsecurity.configs;

import com.example.springsecurity.entities.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // mi tregu qe je klase konfiguruese edhe me hi ne paketen e spring container
public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails employee = User.builder()
//                .username("edona")
//                .password("{noop}edona123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails manager = User.builder()
//                .username("almir")
//                .password("{noop}almir123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("eris")
//                .password("{noop}eris123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        // {noop} passwordi ruhet plain text qashtu qysh e kemi shkru
//        return new InMemoryUserDetailsManager(employee, manager, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", Role.MANAGER.name(), Role.ADMIN.name())//.hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasAnyRole(Role.MANAGER.name(), Role.ADMIN.name()) //.hasRole("MANAGER")//.hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/systems/**").hasRole(Role.ADMIN.name())
//                    .requestMatchers("/products/add").hasRole("MANAGER")
//                    .requestMatchers("/products/update").hasRole("MANAGER")
//                    .requestMatchers("/products/delete").hasRole("ADMIN")
//                    .requestMatchers("/products/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
        ).formLogin(
                form ->
                        form.loginPage("/login") // duhet me ekzistu ni @GetMapping("/login")
                                .loginProcessingUrl("/login") // kju duhet me qene e njejte me th:action ne forme
                                .permitAll()
        ).logout(
                logout -> logout.permitAll()
        ).exceptionHandling(
                config -> config.accessDeniedPage("/access-denied")
        );


        return httpSecurity.build();
    }
}