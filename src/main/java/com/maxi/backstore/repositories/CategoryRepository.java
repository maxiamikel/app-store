package com.maxi.backstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxi.backstore.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
