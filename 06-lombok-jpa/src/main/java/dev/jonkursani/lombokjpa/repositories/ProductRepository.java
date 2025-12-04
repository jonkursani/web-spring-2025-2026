package dev.jonkursani.lombokjpa.repositories;

import dev.jonkursani.lombokjpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    // select count(*) from products where category_id = ?
    int countProductByCategory_Id(Integer categoryId);

    // select * from products where category_id = ?
    List<Product> findAllByCategory_Id(Integer categoryId);

    // unset category reference for all products in a category (bulk update)
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.category = NULL WHERE p.category.id = :categoryId")
    void unsetCategoryForProducts(@Param("categoryId") Integer categoryId);
}