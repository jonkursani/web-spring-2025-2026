package dev.jonkursani.thymeleafexample;

import dev.jonkursani.thymeleafexample.entitites.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/products")
public class ProductController {
    //    static final Set<Product> products = new HashSet<>();
    // per ti sortuar elementet
    static final Set<Product> products = new ConcurrentSkipListSet<>(Comparator.comparingInt(Product::getId));
    // id qe gjenerohet dinamikisht ngjajshem me SQL
    static final AtomicInteger id = new AtomicInteger(1);

    static {
        products.add(new Product(
                id.getAndIncrement(), // 1 -> 2
                "Apple iPhone 17 Pro Max, 256GB, Cosmic Orange",
                "iPhone 17 Pro Max sjell ekranin e madh 6.9 inç Super Retina XDR, me ngjyra të gjalla dhe detaje të jashtëzakonshme. Ceramic Shield 2",
                1699.5,
                "https://iqq6kf0xmf.gjirafa.net/images/1cf1cbb8-76a7-47da-9529-efdba2898b79/1cf1cbb8-76a7-47da-9529-efdba2898b79.webp?width=400"
        ));
        products.add(new Product(
                id.getAndIncrement(), // 2 -> 3
                "Apple iPhone 17 Pro, 256GB, Deep Blue",
                "iPhone 17 Pro Max sjell ekranin e madh 6.9 inç Super Retina XDR, me ngjyra të gjalla dhe detaje të jashtëzakonshme. Ceramic Shield 2",
                1499.5,
                "https://iqq6kf0xmf.gjirafa.net/images/eb211ca2-175b-4384-9727-5a43b23f1ef9/eb211ca2-175b-4384-9727-5a43b23f1ef9.webp?width=400"
        ));
    }

    @GetMapping // route => /
    public String listProducts(Model model) {
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "product/add";
    }

    @PostMapping("/add")
//    public String processProduct() {}
    // method overloading emri i njejte parametrat e ndryshem
    public String add(@Valid @ModelAttribute Product newProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/add";
        }

        newProduct.setId(id.getAndIncrement()); // ex: 3 -> 4
        products.add(newProduct);

        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        var productFromList = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        // nese nuk e ka gjet produktin redirect userin prap te lista
        if (productFromList == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", productFromList);

        return "product/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        // logjika per update
        return "redirect:/products";
    }
}