package com.nakulsiwach.ecommproj.service;
import com.nakulsiwach.ecommproj.exceptions.APIException;
import com.nakulsiwach.ecommproj.exceptions.ResourceNotFoundException;
import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;
import com.nakulsiwach.ecommproj.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()) throw new APIException("NO category till now");

        List<CategoryDTO>categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;

    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB = categoryRepo.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null) {
            throw new APIException("Category already exists with this name:" + category.getCategoryName());
        }
        // categoryRepo.save(category) return the saved object
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
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
    }
}
