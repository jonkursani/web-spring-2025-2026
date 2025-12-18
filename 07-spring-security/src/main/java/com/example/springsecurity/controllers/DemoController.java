package com.example.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping
    public String home() {
        return "home";
    }

    // MANAGER
    @GetMapping("/leaders")
    public String leaders() {
        return "leaders";
    }

    // ADMIN
    @GetMapping("/systems")
    public String systems() {
        return "systems";
    }
}