package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    // Buscar locales por tipo
    List<Local> findByTipoLocal(String tipoLocal);

    // Buscar locales activos
    List<Local> findByEstadoTrue();

    // Consulta personalizada: Locales con stock de un producto específico
    @Query("SELECT l FROM Local l JOIN l.stocks s WHERE s.producto.id = :productoId")
    List<Local> findLocalesConProducto(@Param("productoId") Long productoId);
}
