package com.example.springsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // mi tregu qe je klase konfiguruese edhe me hi ne paketen e spring container
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails employee = User.builder()
                .username("edona")
                .password("{noop}edona123")
                .roles("EMPLOYEE")
                .build();

        UserDetails manager = User.builder()
                .username("almir")
                .password("{noop}almir123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails admin = User.builder()
                .username("eris")
                .password("{noop}eris123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        // {noop} passwordi ruhet plain text qashtu qysh e kemi shkru
        return new InMemoryUserDetailsManager(employee, manager, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
            configurer -> configurer.anyRequest().authenticated()
        ).formLogin(
                form ->
                        form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
        ).logout(logout -> logout.permitAll());


        return httpSecurity.build();
    }
}