package dev.jonkursani.lombokjpa.services;

import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;
import dev.jonkursani.lombokjpa.dtos.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<ProductDto> findAllByTitle(String title);
    ProductDto findById(Integer id);
    ProductDto create(CreateProductRequest createProductRequest);
    ProductDto update(Integer id, UpdateProductRequest updateProductRequest);
    void delete(Integer id);
}