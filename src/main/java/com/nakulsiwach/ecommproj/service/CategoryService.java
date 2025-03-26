package com.nakulsiwach.ecommproj.service;

import com.nakulsiwach.ecommproj.model.Category;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface CategoryService {
    List<Category> getAllCategories();
    void addCategory(Category category);
    String deleteCategory(Long id);
    Category updateCategory(Category category,Long id);
}
