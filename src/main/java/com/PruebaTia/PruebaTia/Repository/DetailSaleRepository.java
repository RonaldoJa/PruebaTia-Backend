package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.DetailSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DetailSaleRepository extends JpaRepository<DetailSale, Long> {

    // Buscar detalles de una venta
    List<DetailSale> findByVentaId(Long ventaId);

    // Buscar detalles de venta por producto
    List<DetailSale> findByProductoId(Long productoId);
}
