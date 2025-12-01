package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor // krijimi i konstruktorit per inicializim
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Category category, BindingResult br) {
        if (br.hasErrors()) {
            return "category/add";
        }

        categoryRepository.save(category);

        return "redirect:/categories";
    }


}