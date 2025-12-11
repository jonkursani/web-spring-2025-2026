package dev.jonkursani.lombokjpa.services;

import dev.jonkursani.lombokjpa.dtos.category.CategoryDto;
import dev.jonkursani.lombokjpa.dtos.category.CreateCategoryRequest;
import dev.jonkursani.lombokjpa.dtos.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto create(CreateCategoryRequest categoryRequest);
    CategoryDto findById(Integer id);
    CategoryDto update(Integer id, UpdateCategoryRequest categoryRequest);
}