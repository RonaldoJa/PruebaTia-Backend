package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.DTO.DashBoardStats;
import com.PruebaTia.PruebaTia.Entity.DTO.LowStockProduct;
import com.PruebaTia.PruebaTia.Entity.DTO.RecentSale;
import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Entity.Sale;
import com.PruebaTia.PruebaTia.Entity.Stock;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.SaleRepository;
import com.PruebaTia.PruebaTia.Repository.StockRepository;
import com.PruebaTia.PruebaTia.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<?>  getDashboardStats() {
        DashBoardStats stats = dashboardService.getDashboardStats();
        ResponseGeneric<DashBoardStats> response = ResponseGeneric.success(stats);
        return ResponseEntity.ok(response);
    }
}
