package dev.jonkursani.lombokjpa.repositories;

import dev.jonkursani.lombokjpa.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
}