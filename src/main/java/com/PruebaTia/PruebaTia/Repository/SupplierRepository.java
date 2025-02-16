package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Buscar proveedores por nombre
    List<Supplier> findByNombreContaining(String nombre);

    // Buscar proveedores por producto
    @Query("SELECT p FROM Supplier p JOIN p.productos pr WHERE pr.id = :productoId")
    List<Supplier> findProveedoresByProductoId(@Param("productoId") Long productoId);
}
