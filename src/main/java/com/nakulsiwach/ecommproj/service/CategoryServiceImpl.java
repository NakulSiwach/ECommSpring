package com.nakulsiwach.ecommproj.service;

import com.nakulsiwach.ecommproj.exceptions.APIException;
import com.nakulsiwach.ecommproj.exceptions.ResourceNotFoundException;
import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService{
//    private List<Category> categories = new ArrayList<>();
    private long nextID = 1L;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()) throw new APIException("NO category till now");
        return categoryRepo.findAll();
    }

    @Override
    public void addCategory(Category category) {
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
//        if (savedCategory != null) {
//            throw new APIException("Category already exists with this name:" + category.getCategoryName());
//        }
        category.setCategoryId(nextID++);
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

//        Category category = categoryRepo.findById(categoryId)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not FOund"));

//        List<Category> categories = categoryRepo.findAll();
//
//        Category category = categories.stream()
//                .filter(c -> c.getCategory_id() == (categoryId))
//                .findFirst()
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not FOund"));
////        if (category == null) return "Category not found";
////        categories.remove(category);
////        categoryRepo.deleteById(categoryId);

        categoryRepo.delete(category);
        return "category with categoryId" + categoryId + " is removed success";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {

        Category savedCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepo.save(category);
        return savedCategory;

//        List<Category> categories = categoryRepo.findAll();
//        Optional<Category> OptCategory = categories.stream()
//                .filter(c -> c.getCategory_id() == (categoryId))
//                .findFirst();
//        if(OptCategory.isPresent()) {
//            Category updatedCategory = OptCategory.get();
//            updatedCategory.setCategory_name(category.getCategory_name());
//            Category updatedCategory2 = categoryRepo.save(updatedCategory);
//            return updatedCategory2;
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not FOund");
//        }
    }
}
