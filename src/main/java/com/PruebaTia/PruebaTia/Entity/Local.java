package com.PruebaTia.PruebaTia.Entity;

import com.PruebaTia.PruebaTia.Entity.Enum.TipoLocal;
import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_local", nullable = false)
    private TipoLocal tipoLocal;

    @Column(nullable = false)
    private Boolean estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sale> ventas;
}

