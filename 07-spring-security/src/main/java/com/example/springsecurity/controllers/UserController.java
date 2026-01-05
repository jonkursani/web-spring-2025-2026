package com.example.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String index() {
        return "user/index";
    }

    @GetMapping("/add")
    public String addUser() {
        return "user/add";
    }
}
