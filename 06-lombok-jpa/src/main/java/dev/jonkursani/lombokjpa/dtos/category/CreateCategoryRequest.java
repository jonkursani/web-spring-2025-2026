package dev.jonkursani.lombokjpa.dtos.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequest { // CreateCategoryRequestDto
    @NotNull(message = "Ju lutem shkruani emrin e kategorise")
    @Size(min = 2, max = 100, message = "Emri i kategorise duhet te jete 2 - 100 karaktere")
    private String name;

    private String description;

    // createdBy
}