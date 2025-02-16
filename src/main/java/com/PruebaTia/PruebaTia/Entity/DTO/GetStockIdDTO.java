package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class GetStockIdDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Integer cantidadDisponible;
    @Getter
    @Setter
    private Integer umbralMinimo;
    @Getter
    @Setter
    private LocalDateTime fechaActualizacion;
    @Getter
    @Setter
    private LocalSaleDTO local;
    @Getter
    @Setter
    private ProductSaleDTO producto;
}
