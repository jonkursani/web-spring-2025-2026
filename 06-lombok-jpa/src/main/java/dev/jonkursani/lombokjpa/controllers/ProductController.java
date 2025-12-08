package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.entities.Product;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import dev.jonkursani.lombokjpa.repositories.ProductRepository;
import dev.jonkursani.lombokjpa.repositories.TagRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor // lombok - i tregon qe duhet me gjeneru konstruktorin me parametra
public class ProductController {
    // DI => Dependency Injection
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

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

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        var categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tagRepository.findAll());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "product/add";
        }

        // nese nuk ekziston produkti e krijon
        // insert into products(title, description, image, price) values (?, ?, ?, ?)

        // validimi per mos me lon me vendos kategori qe nuk ekziston
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            var categoryFromDb = categoryRepository.findById(product.getCategory().getId());
            if (categoryFromDb.isPresent()) {
                product.setCategory(categoryFromDb.get());
            }
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
//        var productFromDb = productRepository.findById(id);
        Optional<Product> productFromDb = productRepository.findById(id);
        List<Category> categories = categoryRepository.findAll();

        if (productFromDb.isEmpty()) {
            return "redirect:/products";
        }

        Product product = productFromDb.get();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "product/update";
        }

        Optional<Product> productFromDb = productRepository.findById(id);

        if (productFromDb.isEmpty()) {
            return "redirect:/products";
        }

        Product productToUpdate = productFromDb.get();

        // product => produkti prej formes
        // productToUpdate => produkti prej DB
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setImage(product.getImage());

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            var categoryFromDb = categoryRepository.findById(product.getCategory().getId());
//            if (categoryFromDb.isPresent()) {
//                productToUpdate.setCategory(categoryFromDb.get());
//            }
            categoryFromDb.ifPresent(productToUpdate::setCategory);
        } else {
            productToUpdate.setCategory(null);
        }

        // nese e gjen produktin me id e bon update
        // update products set title = ?, description = ?, image = ?, price = ? where id = ?
        productRepository.save(productToUpdate);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        Optional<Product> productFromDb = productRepository.findById(id);

        if (productFromDb.isEmpty())
            return "redirect:/products";

        Product productToDelete = productFromDb.get();
        model.addAttribute("productToDelete", productToDelete);
        return "product/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Optional<Product> productFromDb = productRepository.findById(id);

        if (productFromDb.isEmpty())
            return "redirect:/products";

        Product productToDelete = productFromDb.get();

//        productRepository.deleteById(id);
        // delete from products where id = ?
        productRepository.delete(productToDelete);

        return "redirect:/products";
    }
}