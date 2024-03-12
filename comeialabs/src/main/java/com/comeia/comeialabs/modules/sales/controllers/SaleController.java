package com.comeia.comeialabs.modules.sales.controllers;

import com.comeia.comeialabs.modules.sales.entities.Sale;
import com.comeia.comeialabs.modules.sales.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "List Sales", description = "Get a list of all sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of sales retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No sales found")
    })
    public ResponseEntity<List<Sale>> listSales(){
        List<Sale> sales = saleService.listSales();

        if(sales.isEmpty()){
            return new ResponseEntity<>(sales, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update Sale", description = "Update an existing sale by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
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
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create Sale", description = "Create a new sale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
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
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get Sale", description = "Get a sale by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<Sale> getSale(@PathVariable Long id){

        try {
            Sale sale = saleService.getSale(id);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Sale", description = "Delete a sale by ID (Only for ADMIN)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        try {
            saleService.deleteSale(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
