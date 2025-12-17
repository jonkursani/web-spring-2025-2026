package dev.jonkursani.lombokjpa.controllers;

import dev.jonkursani.lombokjpa.dtos.category.CategoryDto;
import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;
import dev.jonkursani.lombokjpa.dtos.product.UpdateProductRequest;
import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.entities.Product;
import dev.jonkursani.lombokjpa.mappers.ProductMapper;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import dev.jonkursani.lombokjpa.repositories.ProductRepository;
import dev.jonkursani.lombokjpa.repositories.TagRepository;
import dev.jonkursani.lombokjpa.services.CategoryService;
import dev.jonkursani.lombokjpa.services.ProductService;
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
    // private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    // @Autowired
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @GetMapping
    public String index(@RequestParam(required = false) String searchText, Model model) {
//        List<Product> products;
        List<ProductDto> products;

        if (searchText != null && !searchText.isBlank()) {
            products = productService.findAllByTitle(searchText);
        } else {
            products = productService.findAll();
        }

        model.addAttribute("searchText", searchText);
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new CreateProductRequest());
//        var categories = categoryRepository.findAll();
        List<CategoryDto> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tagRepository.findAll()); // TODO: TagDto TagService
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") CreateProductRequest product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("tags", tagRepository.findAll()); // TODO: TagDto TagService
            return "product/add";
        }

        // nese nuk ekziston produkti e krijon
        // insert into products(title, description, image, price) values (?, ?, ?, ?)

        // validimi per mos me lon me vendos kategori qe nuk ekziston
//        if (product.getCategory() != null && product.getCategory().getId() != null) {
//            var categoryFromDb = categoryRepository.findById(product.getCategory().getId());
//            if (categoryFromDb.isPresent()) {
//                product.setCategory(categoryFromDb.get());
//            }
//        }

        // productRepository.save(product);
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
//        var productFromDb = productRepository.findById(id);
        // Optional<Product> productFromDb = productRepository.findById(id);
        ProductDto product = productService.findById(id);
        UpdateProductRequest updateProductRequest = productMapper.toUpdateRequest(product);
        List<Category> categories = categoryRepository.findAll();

//        if (productFromDb.isEmpty()) {
        if (product == null) {
            return "redirect:/products";
        }

//        Product product = productFromDb.get();
        model.addAttribute("product", updateProductRequest);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tagRepository.findAll());
        return "product/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("product") UpdateProductRequest product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("tags", tagRepository.findAll());
            return "product/update";
        }

        // Optional<Product> productFromDb = productRepository.findById(id);
//        CategoryDto category = categoryService.findById(id);

        // if (productFromDb.isEmpty()) {
        if (product == null) {
            return "redirect:/products";
        }

//        Product productToUpdate = productFromDb.get();
//
//        // product => produkti prej formes
//        // productToUpdate => produkti prej DB
//        productToUpdate.setTitle(product.getTitle());
//        productToUpdate.setDescription(product.getDescription());
//        productToUpdate.setPrice(product.getPrice());
//        productToUpdate.setImage(product.getImage());
//
//        if (product.getCategory() != null && product.getCategory().getId() != null) {
//            var categoryFromDb = categoryRepository.findById(product.getCategory().getId());
////            if (categoryFromDb.isPresent()) {
////                productToUpdate.setCategory(categoryFromDb.get());
////            }
//            categoryFromDb.ifPresent(productToUpdate::setCategory);
//        } else {
//            productToUpdate.setCategory(null);
//        }

        // nese e gjen produktin me id e bon update
        // update products set title = ?, description = ?, image = ?, price = ? where id = ?
//        productRepository.save(productToUpdate);


        ProductDto updatedProduct = productService.update(id, product);

        // shfaq ni error pse nuk u bo update produkti
        if (updatedProduct == null)
            return "product/update";

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        // Optional<Product> productFromDb = productRepository.findById(id);
        ProductDto product = productService.findById(id);

//        if (productFromDb.isEmpty())
        if (product == null)
            return "redirect:/products";

//        Product productToDelete = productFromDb.get();
        model.addAttribute("productToDelete", product);
        return "product/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        // Optional<Product> productFromDb = productRepository.findById(id);
        ProductDto product = productService.findById(id);

//        if (productFromDb.isEmpty())
        if (product == null)
            return "redirect:/products";

//        Product productToDelete = productFromDb.get();

//        productRepository.deleteById(id);
        // delete from products where id = ?
//        productRepository.delete(productToDelete);
        productService.delete(product.getId());

        return "redirect:/products";
    }
}