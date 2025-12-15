package dev.jonkursani.lombokjpa.services.impls;

import dev.jonkursani.lombokjpa.dtos.category.CategoryDto;
import dev.jonkursani.lombokjpa.dtos.category.CreateCategoryRequest;
import dev.jonkursani.lombokjpa.dtos.category.UpdateCategoryRequest;
import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.mappers.CategoryMapper;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import dev.jonkursani.lombokjpa.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toDto(category)) // konverimi prej entitetit ne dto
                .toList();
    }

    @Override
    public CategoryDto create(CreateCategoryRequest categoryRequest) {
        Category categoryEntity = categoryMapper.toEntity(categoryRequest);
        Category createdCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(createdCategory);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryRepository.findById(id)
                .map(category -> categoryMapper.toDto(category)) // mapimi
                .orElse(null);
    }

    @Override
    public CategoryDto update(Integer id, UpdateCategoryRequest categoryRequest) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryMapper.updateEntityFromDto(categoryRequest, category);
                    return categoryRepository.save(category);
                })
                .map(category -> categoryMapper.toDto(category))
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}