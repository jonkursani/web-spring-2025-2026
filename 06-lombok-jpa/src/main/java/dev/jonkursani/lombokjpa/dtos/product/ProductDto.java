package dev.jonkursani.lombokjpa.dtos.product;

import dev.jonkursani.lombokjpa.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private Integer categoryId;
    private String categoryName;
    // private List<Integer> tagIds = new ArrayList<>();
    // TODO: TagDto
    private List<Tag> tags = new ArrayList<>();
}