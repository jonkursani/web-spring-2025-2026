package dev.jonkursani.lombokjpa.services;

import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<ProductDto> findAllByTitle(String title);
    ProductDto findById(Integer id);
    ProductDto create(CreateProductRequest createProductRequest);
}