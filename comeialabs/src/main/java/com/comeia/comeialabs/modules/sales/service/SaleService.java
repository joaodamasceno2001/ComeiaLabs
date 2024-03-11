package com.comeia.comeialabs.modules.sales.service;

import com.comeia.comeialabs.modules.products.entities.Product;
import com.comeia.comeialabs.modules.products.entities.ProductAcquisition;
import com.comeia.comeialabs.modules.products.service.ProductService;
import com.comeia.comeialabs.modules.sales.entities.Sale;
import com.comeia.comeialabs.modules.sales.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SaleService {

    @Autowired
    private ProductService productService;
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> listSales(){
        return saleRepository.findAll();
    }

    public Sale updateSale(Long saleId, Sale updatedSale) throws Exception {
        Sale existingSale = this.getSale(saleId);
        existingSale.getProducts().clear();
        for (ProductAcquisition productAcquisition : updatedSale.getProducts()) {
            try {
                Product product = productService.getProduct(productAcquisition.getProduct().getId());
                productAcquisition.setProduct(product);
                productAcquisition.setSale(existingSale);
                existingSale.getProducts().add(productAcquisition);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return saleRepository.save(existingSale);
    }

    public Sale createSale(Sale sale){
        Sale sale1 = new Sale();
        Sale newSale= saleRepository.save(sale1);
        sale.getProducts().forEach(productAcquisition -> {
            try {
                Product product = productService.getProduct(productAcquisition.getProduct().getId());
                productAcquisition.setProduct(product);
                productAcquisition.setSale(newSale);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        newSale.setProducts(sale.getProducts());
        return saleRepository.save(newSale);
    }

    public Sale getSale(Long id) throws Exception {

        Optional<Sale> sale = saleRepository.findById(id);

        if(sale.isPresent()) {
            return sale.get();
        }

        throw new Exception("Sale Not Found With Id: " + id);
    }

    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }
}
