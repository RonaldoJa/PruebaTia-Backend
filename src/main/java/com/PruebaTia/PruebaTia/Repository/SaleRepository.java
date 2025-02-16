package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Buscar ventas por local
    List<Sale> findByLocalId(Long localId);

    // Buscar ventas por cliente
    List<Sale> findByClienteId(Long clienteId);

    // Consulta personalizada: Ventas en un rango de fechas
    @Query("SELECT v FROM Sale v WHERE v.fechaVenta BETWEEN :fechaInicio AND :fechaFin")
    List<Sale> findVentasEntreFechas(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    List<Sale> findTop5ByOrderByFechaVentaDesc();
}
