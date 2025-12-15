package dev.jonkursani.lombokjpa.mappers;

import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;
import dev.jonkursani.lombokjpa.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Entity -> Dto
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDto toDto(Product entity);

    // Dto -> Entity
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Product toEntity(CreateProductRequest createProductRequest);
}