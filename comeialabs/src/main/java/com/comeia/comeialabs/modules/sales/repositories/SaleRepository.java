package com.comeia.comeialabs.modules.sales.repositories;

import com.comeia.comeialabs.modules.sales.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
