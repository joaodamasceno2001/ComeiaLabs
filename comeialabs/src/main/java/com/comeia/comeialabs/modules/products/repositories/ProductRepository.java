package com.comeia.comeialabs.modules.products.repositories;

import com.comeia.comeialabs.modules.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
