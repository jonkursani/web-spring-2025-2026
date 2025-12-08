package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "tag/index";
    }
}
