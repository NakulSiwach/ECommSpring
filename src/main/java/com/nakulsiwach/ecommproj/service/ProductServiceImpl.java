package com.nakulsiwach.ecommproj.service;


import com.nakulsiwach.ecommproj.exceptions.ResourceNotFoundException;
import com.nakulsiwach.ecommproj.model.Category;
import com.nakulsiwach.ecommproj.model.Product;
import com.nakulsiwach.ecommproj.payload.ProductDTO;
import com.nakulsiwach.ecommproj.repositories.CategoryRepo;
import com.nakulsiwach.ecommproj.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));

        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01)*product.getPrice());

        product.setCategory(category);
        product.setImage("default.png");
        product.setSpecialPrice(specialPrice);

        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
