package com.PruebaTia.PruebaTia.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "proveedor")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String telefono;

    private String correo;

    private String direccion;

    @ManyToMany(mappedBy = "proveedores")
    private Set<Product> productos;
}