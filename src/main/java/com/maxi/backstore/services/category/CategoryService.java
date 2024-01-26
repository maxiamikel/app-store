package com.maxi.backstore.services.category;

import java.util.List;

import com.maxi.backstore.entities.Category;

public interface CategoryService {

    public Category findCAtegoryById(Long id);

    public Category createCategory(Category category);

    public List<Category> findAllCategories();
}
