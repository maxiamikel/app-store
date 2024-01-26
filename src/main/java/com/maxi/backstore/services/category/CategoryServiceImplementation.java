package com.maxi.backstore.services.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxi.backstore.entities.Category;
import com.maxi.backstore.repositories.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCAtegoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The category id informated not exist in the system"));
    }

    private boolean existsCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            return false;
        }
        return true;
    }

    @Override
    public Category createCategory(Category category) {
        if (this.existsCategoryByName(category.getName())) {
            Category categoryDba = categoryRepository.findByName(category.getName());
            return categoryDba;
        }
        Category categoryDB = new Category();
        categoryDB.setId(null);
        categoryDB.setName(category.getName());
        categoryRepository.save(categoryDB);
        return categoryDB;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categoriesList = new ArrayList<>();
        categoriesList = categoryRepository.findAll();
        return categoriesList;
    }

}
