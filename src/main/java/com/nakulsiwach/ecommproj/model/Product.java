package com.nakulsiwach.ecommproj.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String description;
    private int quantity;
    private double price;
    private double specialPrice;


    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
