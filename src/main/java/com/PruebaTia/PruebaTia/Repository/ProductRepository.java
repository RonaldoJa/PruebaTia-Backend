package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar productos por categoría
    List<Product> findByCategoria(String categoria);

    // Buscar productos activos
    List<Product> findByEstadoTrue();

    // Buscar productos por SKU
    Product findBySku(String sku);

    // Consulta personalizada: Productos con stock menor al umbral mínimo
    @Query("SELECT p FROM Product p JOIN p.stocks s WHERE s.cantidadDisponible < s.umbralMinimo")
    List<Product> findProductosConStockBajo();
}
