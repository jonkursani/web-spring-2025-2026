package dev.jonkursani.lombokjpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data // getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @NotNull(message = "Name is required")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    // default eshte LAZY
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category") // "category" eshte property brenda klases Product
    private List<Product> products = new ArrayList<>();
}