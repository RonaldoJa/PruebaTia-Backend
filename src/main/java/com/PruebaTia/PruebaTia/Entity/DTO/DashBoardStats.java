package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class DashBoardStats {
    @Getter
    @Setter
    private int totalProducts;
    @Getter
    @Setter
    private int totalLocal;
    @Getter
    @Setter
    private int totalSales;
    @Getter
    @Setter
    private BigDecimal totalRevenue;
    @Getter
    @Setter
    private List<RecentSale> recentSales;
    @Getter
    @Setter
    private List<LowStockProduct> lowStockProducts;

}
