package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.DTO.ProductDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.StockDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.SupplierDTO;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.Supplier;
import com.PruebaTia.PruebaTia.Repository.ProductRepository;
import com.PruebaTia.PruebaTia.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productoRepository;
    private SupplierRepository supplierRepository;

    public Product crearProducto(Product producto) {
        if (producto.getProveedores() != null) {
            for (Supplier proveedor : producto.getProveedores()) {
                supplierRepository.findById(proveedor.getId()).ifPresent(proveedorExistente -> {
                    proveedor.setId(proveedorExistente.getId());
                });
            }
        }
        producto.setFechaCreacion(LocalDateTime.now());
        return productoRepository.save(producto);
    }

    public List<ProductDTO> listarProductos() {
        return productoRepository.findAll().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setSku(product.getSku());
            dto.setNombre(product.getNombre());
            dto.setCategoria(product.getCategoria());
            dto.setDescripcion(product.getDescripcion());
            dto.setPrecioBase(product.getPrecioBase());
            dto.setEstado(product.getEstado());
            dto.setFechaCreacion(product.getFechaCreacion());

            Set<SupplierDTO> proveedoresDTO = product.getProveedores().stream().map(supplier -> {
                SupplierDTO sDTO = new SupplierDTO();
                sDTO.setId(supplier.getId());
                sDTO.setNombre(supplier.getNombre());
                return sDTO;
            }).collect(Collectors.toSet());
            dto.setProveedores(proveedoresDTO);

            Set<StockDTO> stocksDTO = product.getStocks().stream().map(stock -> {
                StockDTO stDTO = new StockDTO();
                stDTO.setId(stock.getId());
                stDTO.setCantidadDisponible(stock.getCantidadDisponible());
                return stDTO;
            }).collect(Collectors.toSet());
            dto.setStocks(stocksDTO);

            return dto;
        }).collect(Collectors.toList());
    }

    public Product obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product actualizarProducto(Long id, Product producto) {
        Product productoExistente = obtenerProducto(id);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecioBase(producto.getPrecioBase());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setEstado(producto.getEstado());
        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}