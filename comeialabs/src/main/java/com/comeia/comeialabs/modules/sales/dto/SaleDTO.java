package com.comeia.comeialabs.modules.sales.dto;

import com.comeia.comeialabs.modules.products.dto.ProductDTO;
import com.comeia.comeialabs.modules.sales.entities.Sale;
import com.comeia.comeialabs.modules.users.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {

    private Long id;

    private UserDTO user;

    private List<ProductDTO> products;

    private LocalDateTime createdAt;

    public SaleDTO(Sale sale){
       this.id = sale.getId();
       //this.user = new UserDTO(sale.getUser());
     
       this.createdAt = sale.getCreatedAt();
    }

}
