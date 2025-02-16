package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.DTO.DashBoardStats;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductDTO;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productoService;

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Product producto) {
        Product nuevoProducto = productoService.crearProducto(producto);
        ResponseGeneric<Product> response = ResponseGeneric.success(nuevoProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> listarProductos() {
        ResponseGeneric<List<ProductDTO>> response = ResponseGeneric.success(productoService.listarProductos());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long id) {
        ResponseGeneric<Product> response = ResponseGeneric.success(productoService.obtenerProducto(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Product producto) {
        productoService.actualizarProducto(id, producto);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }
}
