package com.nakulsiwach.ecommproj.service;

import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;


public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long id);
    Category updateCategory(Category category,Long id);
}
