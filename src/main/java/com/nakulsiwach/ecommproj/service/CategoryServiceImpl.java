package com.nakulsiwach.ecommproj.service;
import com.nakulsiwach.ecommproj.exceptions.APIException;
import com.nakulsiwach.ecommproj.exceptions.ResourceNotFoundException;
import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.payload.CategoryDTO;
import com.nakulsiwach.ecommproj.payload.CategoryResponse;
import com.nakulsiwach.ecommproj.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepo.findAll(pageDetails);

        // instead of categoryRepo.findall
        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty()) throw new APIException("NO category till now");

        List<CategoryDTO>categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());

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
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        Category category1 = modelMapper.map(categoryDTO,Category.class);
        category1.setCategoryId(categoryId);
        savedCategory = categoryRepo.save(category1);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
