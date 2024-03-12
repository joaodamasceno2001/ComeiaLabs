package com.comeia.comeialabs.modules.sales.service;

import com.comeia.comeialabs.modules.products.entities.Product;
import com.comeia.comeialabs.modules.products.entities.ProductAcquisition;
import com.comeia.comeialabs.modules.products.service.ProductService;
import com.comeia.comeialabs.modules.sales.entities.Sale;
import com.comeia.comeialabs.modules.sales.repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleService saleService;

    private Sale sale;
    private Product product;

    @BeforeEach
    public void setup() {
        sale = new Sale();
        product = new Product();
        product.setId(1L);

        List<ProductAcquisition> products = new ArrayList<>();
        ProductAcquisition productAcquisition = new ProductAcquisition();
        productAcquisition.setId(1L);
        productAcquisition.setQuantity(1);
        productAcquisition.setProduct(product);
        products.add(productAcquisition);
        sale.setProducts(products);
    }

    @Test
    public void testUpdateSale() throws Exception {
        Sale existingSale = new Sale();
        existingSale.setId(1L);
        existingSale.setProducts(new ArrayList<>());

        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(existingSale));
        when(productService.getProduct(anyLong())).thenReturn(product);

        when(saleService.updateSale(anyLong(), any(Sale.class))).thenReturn(existingSale);

        Sale updatedSale = saleService.updateSale(1L, sale);

        assertEquals(1, updatedSale.getProducts().size());
        verify(saleRepository, times(1)).findById(anyLong());
        verify(productService, times(1)).getProduct(anyLong());
        verify(saleRepository, times(1)).save(any(Sale.class));
    }

    @Test
    public void testCreateSale() throws Exception {
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);
        when(productService.getProduct(anyLong())).thenReturn(product);

        Sale createdSale = saleService.createSale(sale);

        assertEquals(sale, createdSale);
        verify(saleRepository, times(1)).save(any(Sale.class));
        verify(productService, times(1)).getProduct(anyLong());
    }

    @Test
    public void testGetSale() throws Exception {
        Sale existingSale = new Sale();
        existingSale.setId(1L);

        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(existingSale));

        Sale foundSale = saleService.getSale(1L);

        assertEquals(existingSale, foundSale);
        verify(saleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testDeleteSale() {
        saleService.deleteSale(1L);

        verify(saleRepository, times(1)).deleteById(anyLong());
    }
}
