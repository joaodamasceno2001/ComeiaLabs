package com.comeia.comeialabs.modules.products.service;

import com.comeia.comeialabs.modules.products.entities.Product;
import com.comeia.comeialabs.modules.products.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should list products")
    void listProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 100));
        products.add(new Product(2L, "Product 2", 200));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.listProducts();

        assertThat(result).isEqualTo(products);
    }

    @Test
    @DisplayName("Should update product when product exists")
    void updateProduct_productExists() throws Exception {
        Long id = 1L;
        Product updatedProduct = new Product(1L, "Updated Product", 150);
        Product originalProduct = new Product(1L, "Original Product", 100);

        when(productRepository.findById(id)).thenReturn(Optional.of(originalProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.updateProduct(id, updatedProduct);

        assertThat(result).isEqualTo(updatedProduct);
        assertThat(result.getDescription()).isEqualTo("Updated Product");
        assertThat(result.getPrice()).isEqualTo(150);
    }

    @Test
    @DisplayName("Should throw exception when updating product if product not found")
    void updateProduct_productNotFound() {
        Long id = 1L;
        Product updatedProduct = new Product(1L, "Updated Product", 150);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> productService.updateProduct(id, updatedProduct));
    }

    @Test
    @DisplayName("Should create Product successfully")
    void createProduct() {
        Product product = new Product(1L, "Test Product", 500);

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertThat(result).isEqualTo(product);
    }

    @Test
    @DisplayName("Should get product when product exists")
    void getProduct_productExists() throws Exception {
        Long id = 1L;
        Product product = new Product(id, "Test Product", 500);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(id);

        assertThat(result).isEqualTo(product);
    }

    @Test
    @DisplayName("Should throw exception when getting product if product not found")
    void getProduct_productNotFound() {
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> productService.getProduct(id));
    }

    @Test
    @DisplayName("Should delete product successfully")
    void deleteProduct() {
        Long id = 1L;

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }
}
