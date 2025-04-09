package com.nakulsiwach.ecommproj.service;


import com.nakulsiwach.ecommproj.model.Product;
import com.nakulsiwach.ecommproj.payload.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
