package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecentSale {
    @Getter
    @Setter
    private Long saleId;
    @Getter
    @Setter
    private LocalDateTime saleDate;
    @Getter
    @Setter
    private BigDecimal saleAmount;
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private String LocalName;


}
