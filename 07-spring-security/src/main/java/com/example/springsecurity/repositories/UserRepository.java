package com.example.springsecurity.repositories;

import com.example.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // findAll()
    // findById()
    // save()
    // delete()
    Optional<User> findByUsername(String username);
}