package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProductDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String sku;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private BigDecimal precioBase;
    @Getter
    @Setter
    private String categoria;
    @Getter
    @Setter
    private Boolean estado;
    @Getter
    @Setter
    private LocalDateTime fechaCreacion;

    @Getter
    @Setter
    private Set<SupplierDTO> proveedores;
    @Getter
    @Setter
    private Set<StockDTO> stocks;
}
