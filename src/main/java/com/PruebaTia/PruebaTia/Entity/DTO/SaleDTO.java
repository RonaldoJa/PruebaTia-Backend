package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class SaleDTO {
    @Getter
    @Setter
    private Long saleId;
    @Getter
    @Setter
    private LocalSaleDTO local;
    @Getter
    @Setter
    private BigDecimal total;
    @Getter
    @Setter
    private List<DetailSaleDTO> detalles;
}
