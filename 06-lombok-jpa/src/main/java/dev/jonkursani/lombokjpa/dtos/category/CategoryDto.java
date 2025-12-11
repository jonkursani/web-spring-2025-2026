package dev.jonkursani.lombokjpa.dtos.category;

import lombok.Data;

// listimin e kategorive
@Data // geterat dhe seterat
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;

    // createdAt
    // updatedAt
}