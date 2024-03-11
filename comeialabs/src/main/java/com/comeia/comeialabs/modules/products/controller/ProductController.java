package com.comeia.comeialabs.modules.products.controller;

import com.comeia.comeialabs.modules.products.entities.Product;
import com.comeia.comeialabs.modules.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> Products = productService.listProducts();

        if(Products.isEmpty()){
            return new ResponseEntity<>(Products, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product ProductUpdated){

        try {
            Product Product = productService.updateProduct(id, ProductUpdated);
            return new ResponseEntity<>(Product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> createProduct(@RequestBody Product Product){
        try {
            Product newProduct = productService.createProduct(Product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){

        try {
            Product Product = productService.getProduct(id);
            return new ResponseEntity<>(Product, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
