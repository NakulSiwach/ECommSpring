package com.nakulsiwach.ecommproj.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long productId;
    private String productName;
//    private String image;
    private double price;
    private String description;
    private double discount;
//    private double specialPrice;
    private Integer quantity;
}
