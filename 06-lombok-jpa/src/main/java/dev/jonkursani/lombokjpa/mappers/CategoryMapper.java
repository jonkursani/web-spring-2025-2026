package dev.jonkursani.lombokjpa.mappers;

import dev.jonkursani.lombokjpa.dtos.category.CategoryDto;
import dev.jonkursani.lombokjpa.dtos.category.CreateCategoryRequest;
import dev.jonkursani.lombokjpa.dtos.category.UpdateCategoryRequest;
import dev.jonkursani.lombokjpa.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// mapperi i tregon se qysh mapohet entiteti ne dto dhe e kunderta
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Entity -> dto
    CategoryDto toDto(Category category);

    // dto -> entity
    Category toEntity(CategoryDto categoryDto);
    Category toEntity(CreateCategoryRequest categoryRequest);
    void updateEntityFromDto(UpdateCategoryRequest categoryRequest, @MappingTarget Category category);
}