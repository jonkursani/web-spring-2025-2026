package dev.jonkursani.lombokjpa.mappers;

import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;
import dev.jonkursani.lombokjpa.dtos.product.UpdateProductRequest;
import dev.jonkursani.lombokjpa.entities.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    UpdateProductRequest toUpdateRequest(ProductDto productDto);

    void updateEntityFromDto(UpdateProductRequest dto, @MappingTarget Product entity);

    @AfterMapping
    default void fillTagIds(ProductDto productDto, @MappingTarget UpdateProductRequest updateProductRequest) {
        if (productDto.getTags() == null) {
            return;
        }

        updateProductRequest.setTagIds(productDto.getTags().stream().map(tag -> tag.getId()).toList());
    }
}