package dev.jonkursani.lombokjpa.dtos.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull(message = "Ju lutem shkruani titullin")
    @Size(min = 2, max = 100, message = "Title duhet te jete 2 deri 100 karaktere")
    private String title;

    @NotNull(message = "Ju lutem shkruani pershkrimin")
    @Size(min = 10, max = 255, message = "Pershkrimi duhet te jete 10 deri 255 karaktere")
    private String description;

    @NotNull(message = "Ju lutem shkruani cmimin")
    @Min(value = 0, message = "Cmimi duhet te jete me i madh ose i barabarte me 0")
    private Double price;

    @NotNull(message = "Ju lutem vendosni foton")
    private String image;

    private Integer categoryId;

    private List<Integer> tagIds = new ArrayList<>();
    // createdBy
}