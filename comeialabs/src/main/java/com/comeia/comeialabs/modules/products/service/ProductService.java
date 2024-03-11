package com.comeia.comeialabs.modules.products.service;

import com.comeia.comeialabs.modules.products.entities.Product;
import com.comeia.comeialabs.modules.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product ProductUpdated) throws Exception {

        Optional<Product> productOpt = productRepository.findById(id);

        if(productOpt.isPresent()){
            Product product = productOpt.get();
            product.setDescription(ProductUpdated.getDescription());
            product.setPrice(ProductUpdated.getPrice());
            return productRepository.save(productOpt.get());
        }

        throw new Exception("Product Not Found With Id: " + id);
    }

    public Product createProduct(Product Product){
        return productRepository.save(Product);
    }

    public Product getProduct(Long id) throws Exception {

        Optional<Product> Product = productRepository.findById(id);

        if(Product.isPresent()) {
            return Product.get();
        }

        throw new Exception("Product Not Found With Id: " + id);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
