package com.comeia.comeialabs.modules.sales.controllers;

import com.comeia.comeialabs.modules.sales.dto.SaleDTO;
import com.comeia.comeialabs.modules.sales.entities.Sale;
import com.comeia.comeialabs.modules.sales.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@Slf4j
@Tag(name = "comeia-labs")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> listSales(){
        List<Sale> sales = saleService.listSales();

        if(sales.isEmpty()){
            return new ResponseEntity<>(sales, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale saleUpdated){

        try {
            Sale sale = saleService.updateSale(id, saleUpdated);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale){
        try {
            Sale newSale = saleService.createSale(sale);
            return new ResponseEntity<>(newSale, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSale(@PathVariable Long id){

        try {
            Sale sale = saleService.getSale(id);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        try {
            saleService.deleteSale(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
