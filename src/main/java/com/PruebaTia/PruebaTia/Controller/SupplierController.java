package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.Supplier;
import com.PruebaTia.PruebaTia.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proveedores")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplierDetails) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setNombre(supplierDetails.getNombre());
            supplier.setTelefono(supplierDetails.getTelefono());
            supplier.setCorreo(supplierDetails.getCorreo());
            supplier.setDireccion(supplierDetails.getDireccion());
            // Si es necesario, actualiza también la relación con los productos
            Supplier updatedSupplier = supplierRepository.save(supplier);
            return ResponseEntity.ok(updatedSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar proveedores por nombre (parcial)
    @GetMapping("/search")
    public List<Supplier> searchSuppliersByName(@RequestParam("nombre") String nombre) {
        return supplierRepository.findByNombreContaining(nombre);
    }

    // Buscar proveedores por ID de producto
    @GetMapping("/producto/{productoId}")
    public List<Supplier> getSuppliersByProductoId(@PathVariable Long productoId) {
        return supplierRepository.findProveedoresByProductoId(productoId);
    }
}
