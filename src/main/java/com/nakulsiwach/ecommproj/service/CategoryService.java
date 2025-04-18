package com.nakulsiwach.ecommproj.service;

import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;


public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long id);
}
