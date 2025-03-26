package com.nakulsiwach.ecommproj.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="CategoriesTable")
public class Category {
//   in entity, getters and setters are imp as it is used by JPA

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @NotBlank
    @Size(min = 5, message = "Categorty name must contain 5 letters minimin")
    private String categoryName;

//    public Category(long category_id, String category_name) {
//        this.category_id = category_id;
//        this.category_name = category_name;
//    }
//    public Category(){
//    }
//    public long getCategory_id() {
//        return category_id;
//    }
//    public void setCategory_id(long category_id) {
//        this.category_id = category_id;
//    }
//    public String getCategory_name() {
//        return category_name;
//    }
//    public void setCategory_name(String category_name) {
//        this.category_name = category_name;
//    }
}
