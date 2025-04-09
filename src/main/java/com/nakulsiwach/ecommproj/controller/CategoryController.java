package com.nakulsiwach.ecommproj.controller;
import com.nakulsiwach.ecommproj.config.AppConstants;
import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;
import com.nakulsiwach.ecommproj.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name="message") String message) {
//        return new ResponseEntity<>("Echoed Mssg : " + message,HttpStatus.OK);
//    }

    @GetMapping("public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "PageNumber",defaultValue = AppConstants.PAGE_NUMBER)Integer pageNumber,
            @RequestParam(name= "PageSize",defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name ="Sortby",defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(name ="SortOrder",defaultValue = AppConstants.SORT_ORDER) String sortOrder
    ){
        CategoryResponse categoryResponse =  categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO CategoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.addCategory(CategoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
    }

    @PutMapping("public/categories/{categoryID}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryID){
        CategoryDTO SavedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryID);
        return new ResponseEntity<>(SavedCategoryDTO, HttpStatus.OK);
    }

}
