package dev.jonkursani.firstproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Controller // returns HTML
//@RestController // returns JSON
public class HomeController {
    private Set<String> todos = new HashSet<>();

    @GetMapping
    public String home(Model model) {
        todos.add("Todo 1");
        todos.add("Todo 2");
        todos.add("Todo 3");
        todos.add("Todo 4");
        todos.add("Todo 5");
        model.addAttribute("todos", todos);
        model.addAttribute("title", "Hello from Spring Boot");
        return "home";
    }
}
