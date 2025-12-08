package dev.jonkursani.lombokjpa.repositories;

import dev.jonkursani.lombokjpa.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    // findAll()
    // findById()
    // save()
    // delete()
}