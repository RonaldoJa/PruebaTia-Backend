package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

public class ProductSaleDTO {
    @Getter
    @Setter
    private Long productId;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private Integer cantidad;
}
