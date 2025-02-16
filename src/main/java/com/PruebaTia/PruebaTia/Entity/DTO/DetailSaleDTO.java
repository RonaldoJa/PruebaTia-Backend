package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

public class DetailSaleDTO {
    @Getter
    @Setter
    private Long detailId;
    @Getter
    @Setter
    private ProductSaleDTO product;
}
