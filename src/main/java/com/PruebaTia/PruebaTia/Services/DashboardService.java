package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.DTO.DashBoardStats;
import com.PruebaTia.PruebaTia.Entity.DTO.LowStockProduct;
import com.PruebaTia.PruebaTia.Entity.DTO.RecentSale;
import com.PruebaTia.PruebaTia.Entity.Sale;
import com.PruebaTia.PruebaTia.Entity.Stock;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.SaleRepository;
import com.PruebaTia.PruebaTia.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;


    public DashBoardStats getDashboardStats() {
        DashBoardStats stats = new DashBoardStats();

        stats.setTotalProducts((int) productRepository.count());
        stats.setTotalLocal((int) localRepository.count());
        stats.setTotalSales((int) saleRepository.count());

        BigDecimal totalRevenue = saleRepository.findAll()
                .stream()
                .map(Sale::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setTotalRevenue(totalRevenue);

        List<RecentSale> recentSaleDTOs = saleRepository.findTop5ByOrderByFechaVentaDesc()
                .stream()
                .map(this::convertToRecentSale)
                .collect(Collectors.toList());
        stats.setRecentSales(recentSaleDTOs);

        List<LowStockProduct> lowStockProducts = stockRepository.findLowStock()
                .stream()
                .map(this::convertToLowStockProduct)
                .collect(Collectors.toList());
        stats.setLowStockProducts(lowStockProducts);

        return stats;
    }

    private RecentSale convertToRecentSale(Sale sale) {
        RecentSale rs = new RecentSale();
        rs.setSaleId(sale.getId());
        rs.setSaleDate(sale.getFechaVenta());
        rs.setSaleAmount(sale.getTotal());
        rs.setLocalName(sale.getLocal() != null ? sale.getLocal().getNombre() : "Sin local");
        rs.setProductName(
                sale.getDetalles() != null && !sale.getDetalles().isEmpty()
                        ? sale.getDetalles().iterator().next().getProducto().getNombre()
                        : "Sin producto"
        );
        return rs;
    }

    private LowStockProduct convertToLowStockProduct(Stock stock) {
        LowStockProduct lp = new LowStockProduct();
        lp.setProductId(stock.getProducto().getId());
        lp.setProductName(stock.getProducto().getNombre());
        lp.setStock(stock.getCantidadDisponible());
        return lp;
    }

}
