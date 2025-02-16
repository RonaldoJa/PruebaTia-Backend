package com.PruebaTia.PruebaTia.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "venta_detalle")
public class DetailSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference
    private Sale venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Product producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private BigDecimal subtotal;


}
