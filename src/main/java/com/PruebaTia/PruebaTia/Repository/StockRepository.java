package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Local;
import com.PruebaTia.PruebaTia.Entity.Product;
import com.PruebaTia.PruebaTia.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByProductoIdAndLocalId(Long productoId, Long localId);

    List<Stock> findByProductoId(Long productoId);

    Stock findByLocalIdAndProductoId(Long localId, Long productoId);

    Optional<Stock> findByProductoAndLocal(Product producto, Local local);

    @Query("SELECT SUM(s.cantidadDisponible) FROM Stock s WHERE s.producto.id = :productoId")
    Integer findStockTotalByProductoId(@Param("productoId") UUID productoId);

    @Query("SELECT s FROM Stock s WHERE s.cantidadDisponible < s.umbralMinimo")
    List<Stock> findLowStock();
}
