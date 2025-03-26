package com.nakulsiwach.ecommproj.repositories;

import com.nakulsiwach.ecommproj.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;


//entity,type of primary key
public interface CategoryRepo extends JpaRepository<Category, Long> {

    // JPA is giving auto implementations, no need to write custom queries ,naming shi se bass

    Category findByCategoryName(@NotBlank @Size(min = 5, message = "Categorty name must contain 5 letters minimin") String categoryName);
}