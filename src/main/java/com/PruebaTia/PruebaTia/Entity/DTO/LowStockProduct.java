package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

public class LowStockProduct {
    @Getter
    @Setter
    private Long productId;
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private int stock;
}
