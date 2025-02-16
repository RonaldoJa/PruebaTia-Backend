package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.*;
import com.PruebaTia.PruebaTia.Entity.DTO.GetStockIdDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductAssign;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.StockRepository;
import com.PruebaTia.PruebaTia.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stocks")
@CrossOrigin(origins = "http://localhost:5173")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<?> getObtenerStock() {
        ResponseGeneric<List<GetStockIdDTO>> response = ResponseGeneric.success(stockService.listarStocks());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> asignarStock(@RequestBody ProductAssign productAssign) {
        ProductAssign nuevoStock = stockService.crearStock(productAssign);
        ResponseGeneric<ProductAssign> response = ResponseGeneric.success(nuevoStock);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/local/{localId}/producto/{productoId}")
    public ResponseEntity<?> obtenerStock(@PathVariable Long localId, @PathVariable Long productoId) {
        ResponseGeneric<GetStockIdDTO> response = ResponseGeneric.success(stockService.obtenerStock(localId, productoId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/local/{localId}/producto/{productoId}")
    public ResponseEntity<?> actualizarStock(
            @PathVariable Long localId,
            @PathVariable Long productoId,
            @RequestParam Integer cantidad) {
        stockService.actualizarStock(localId, productoId, cantidad);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }
}
