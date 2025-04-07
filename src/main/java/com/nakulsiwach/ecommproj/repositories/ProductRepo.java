package com.nakulsiwach.ecommproj.repositories;

import com.nakulsiwach.ecommproj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
