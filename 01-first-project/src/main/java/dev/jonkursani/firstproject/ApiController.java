package dev.jonkursani.firstproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // returns JSON // bashkimi i @Controller dhe @ResponseBody
@RequestMapping("/api") // path prefix
public class ApiController {
    @GetMapping("")
    public String hello() {
        return "Hello from Spring Boot";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello from test";
    }
}
