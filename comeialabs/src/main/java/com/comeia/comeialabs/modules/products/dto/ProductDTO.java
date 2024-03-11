package com.comeia.comeialabs.modules.products.dto;


import com.comeia.comeialabs.modules.products.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private String description;

    private double price;

    private int quantity;

    public ProductDTO(Product product){
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
