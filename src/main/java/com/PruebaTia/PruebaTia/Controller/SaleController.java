package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.DTO.SaleDTO;
import com.PruebaTia.PruebaTia.Entity.DetailSale;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Entity.Sale;
import com.PruebaTia.PruebaTia.Repository.ClientRepository;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import com.PruebaTia.PruebaTia.Repository.SaleRepository;
import com.PruebaTia.PruebaTia.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ventas")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody Sale venta) {
        Sale nuevaVenta = saleService.crearVenta(venta);
        ResponseGeneric<Sale> response = ResponseGeneric.success(nuevaVenta);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> listarVentas() {
        ResponseGeneric<List<SaleDTO>> response = ResponseGeneric.success(saleService.listarVentas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVenta(@PathVariable Long id) {
        ResponseGeneric<Sale> response = ResponseGeneric.success(saleService.obtenerVenta(id));
        return ResponseEntity.ok(response);
    }
}
