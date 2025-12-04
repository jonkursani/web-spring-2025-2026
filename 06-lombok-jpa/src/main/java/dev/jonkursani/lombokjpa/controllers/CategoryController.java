package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.entities.Product;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import dev.jonkursani.lombokjpa.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private final ProductRepository productRepository;

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            return "redirect:/categories";

        Category categoryToDelete = categoryFromDb.get();
        // select count(*) from products where category_id = id
        int productsCount = productRepository.countProductByCategory_Id(categoryToDelete.getId());

        model.addAttribute("categoryToDelete", categoryToDelete);
        model.addAttribute("productsCount", productsCount);

        return "category/delete";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteCategory(@PathVariable int id, Model model) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            return "redirect:/categories";

        Category categoryToDelete = categoryFromDb.get();
        int productsCount = productRepository.countProductByCategory_Id(categoryToDelete.getId());

        // validimi ne server side nese dikush e bon enable butonin per delete ne html
        if (productsCount > 0) {
            model.addAttribute("categoryToDelete", categoryToDelete);
            model.addAttribute("productsCount", productsCount);
            model.addAttribute("errorMessage", "Ju nuk mund te fshini kete kategori sepse ka produkte");

            // nese doni me i bo komplet produktet null manualisht jo e mire nese ka shume te dhena
//        List<Product> products = productRepository.findAllByCategory_Id(categoryToDelete.getId());
//
//        for (Product p : products) {
//            p.setCategory(null);
//            productRepository.save(p);
//        }

            // nese doni me i bo komplet produktet null manualisht menyre me mire nese ka shume te dhena
            // productRepository.unsetCategoryForProducts(categoryToDelete.getId());

            return "category/delete";
        }

        // delete category where id = categoryToDelete.getId()
        categoryRepository.deleteById(categoryToDelete.getId());

        return "redirect:/categories";
    }

    @GetMapping("/{id}/products")
    public String products(@PathVariable int id, Model model) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            return "redirect:/categories";

        Category category = categoryFromDb.get();
        List<Product> products = productRepository.findAllByCategory_Id(category.getId());

        model.addAttribute("category", category);
        model.addAttribute("products", products);

        return "category/products";
    }


}