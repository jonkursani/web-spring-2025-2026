package com.example.springsecurity.controllers.api;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;
    // GET => /api/users
    // GET by id => /api/users/1
    // POST => /api/users
    // PUT => /api/users/id
    // DELETE => /api/users/id
    // ResponseEntity
    // 200 OK
    // 201 Created
    // 404 Not found
    // 403 Conflict
    // 400 Bad Requst
    // 500 Internal Server Error
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
//        return "user/index"
        var users = userRepository.findAll();
        var userDto = users.stream()
                .map(user -> new User(user.getId(), user.getUsername(), "", user.getRole()))
                .toList();
        return ResponseEntity.ok(userDto);
//        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}