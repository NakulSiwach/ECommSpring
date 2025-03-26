package com.nakulsiwach.ecommproj.controller;

import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    // to access methods of categoryserive, vahi toh dega categories (get/post..)
    @Autowired
    private CategoryService categoryService;
//    using field injection instead of constructior injection
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping("public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories =  categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category){
        categoryService.addCategory(category);
        return new ResponseEntity<>("category added successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String Status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(Status, HttpStatus.OK);
//            return ResponseEntity.ok(Status);
//            return ResponseEntity.status(HttpStatus.OK).body(Status);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("public/categories/{categoryID}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryID){
        try {
            Category SavedCategory = categoryService.updateCategory(category,categoryID);
            return new ResponseEntity<>("category has been updated with ID : " + categoryID, HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}
