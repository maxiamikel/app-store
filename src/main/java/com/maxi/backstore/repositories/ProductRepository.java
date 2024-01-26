package com.maxi.backstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maxi.backstore.entities.Category;
import com.maxi.backstore.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.status = CREATED")
    public List<Product> findAllProduct();

    public Product findByName(String name);

}
