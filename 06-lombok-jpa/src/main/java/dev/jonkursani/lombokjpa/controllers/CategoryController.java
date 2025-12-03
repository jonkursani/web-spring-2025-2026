package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            return "redirect:/categories";

        model.addAttribute("category", categoryFromDb.get());
        return "category/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute Category category, BindingResult br) {
        if (br.hasErrors())
            return "category/update";

        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            return "redirect:/categories";

        Category categoryToUpdate = categoryFromDb.get();
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());

        categoryRepository.save(categoryToUpdate);

        return "redirect:/categories";
    }
}