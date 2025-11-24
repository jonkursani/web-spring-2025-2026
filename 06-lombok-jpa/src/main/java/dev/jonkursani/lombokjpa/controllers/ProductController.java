package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.entities.Product;
import dev.jonkursani.lombokjpa.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor // lombok - i tregon qe duhet me gjeneru konstruktorin me parametra
public class ProductController {
    // DI => Dependency Injection
    private final ProductRepository productRepository;

    // @Autowired
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @GetMapping
    public String index(@RequestParam(required = false) String searchText, Model model) {
        List<Product> products;

        if (searchText != null && !searchText.isBlank()) {
            products = productRepository.findAllByTitleContainingIgnoreCase(searchText);
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("searchText", searchText);
        model.addAttribute("products", products);
        return "product/index";
    }





}