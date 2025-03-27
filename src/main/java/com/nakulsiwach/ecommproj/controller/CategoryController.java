package com.nakulsiwach.ecommproj.controller;
import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;
import com.nakulsiwach.ecommproj.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(){
        CategoryResponse categoryResponse =  categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO CategoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.addCategory(CategoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        String Status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(Status, HttpStatus.OK);
    }

    @PutMapping("public/categories/{categoryID}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryID){
        Category SavedCategory = categoryService.updateCategory(category,categoryID);
        return new ResponseEntity<>("category has been updated with ID : " + categoryID, HttpStatus.OK);
    }

}
