package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ProductAssign {
    @Setter
    @Getter
    private Long productoId;
    @Setter
    @Getter
    private Long localId;
    @Setter
    @Getter
    private Integer cantidadDisponible;
    @Setter
    @Getter
    private Integer umbralMinimo;



}
