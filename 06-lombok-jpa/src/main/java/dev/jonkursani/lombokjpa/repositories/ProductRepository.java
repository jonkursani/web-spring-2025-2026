package dev.jonkursani.lombokjpa.repositories;

import dev.jonkursani.lombokjpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Na gjenerohen metodat e gatshme
    // findAll
    // findById
    // save
    // delete
    // Select * from products where title like ?
    List<Product> findAllByTitleContainingIgnoreCase(String title);
}