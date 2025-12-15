package dev.jonkursani.lombokjpa.services.impls;

import dev.jonkursani.lombokjpa.dtos.product.CreateProductRequest;
import dev.jonkursani.lombokjpa.dtos.product.ProductDto;
import dev.jonkursani.lombokjpa.entities.Category;
import dev.jonkursani.lombokjpa.entities.Product;
import dev.jonkursani.lombokjpa.entities.Tag;
import dev.jonkursani.lombokjpa.mappers.ProductMapper;
import dev.jonkursani.lombokjpa.repositories.CategoryRepository;
import dev.jonkursani.lombokjpa.repositories.ProductRepository;
import dev.jonkursani.lombokjpa.repositories.TagRepository;
import dev.jonkursani.lombokjpa.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
//                .map(product -> productMapper.toDto(product))
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> findAllByTitle(String title) {
        return productRepository.findAllByTitleContainingIgnoreCase(title)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductDto create(CreateProductRequest createProductRequest) {
        Product product = productMapper.toEntity(createProductRequest);

        // kategorija mundet mos me ekzistu
        Category category = categoryRepository
                .findById(createProductRequest.getCategoryId())
                .orElse(null);

        // setCategory
        if (createProductRequest.getCategoryId() != null)
            product.setCategory(category);
        else
            product.setCategory(null);

        // setTags
        if (createProductRequest.getTagIds() != null && !createProductRequest.getTagIds().isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(createProductRequest.getTagIds());
            product.setTags(tags);
        } else {
            product.setTags(null);
        }

        Product savedProduct = productRepository.save(product);
        ProductDto productDto = productMapper.toDto(savedProduct);
        return productDto;
    }
}