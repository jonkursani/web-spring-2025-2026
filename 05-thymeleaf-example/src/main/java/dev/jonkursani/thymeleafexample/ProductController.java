package dev.jonkursani.thymeleafexample;

import dev.jonkursani.thymeleafexample.entitites.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    static final Set<Product> products = new HashSet<>();

    static {
        products.add(new Product(
                1,
                "Apple iPhone 17 Pro Max, 256GB, Cosmic Orange",
                "iPhone 17 Pro Max sjell ekranin e madh 6.9 inç Super Retina XDR, me ngjyra të gjalla dhe detaje të jashtëzakonshme. Ceramic Shield 2",
                1699.5,
                "https://iqq6kf0xmf.gjirafa.net/images/1cf1cbb8-76a7-47da-9529-efdba2898b79/1cf1cbb8-76a7-47da-9529-efdba2898b79.webp?width=400"
        ));
        products.add(new Product(
                2,
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








}