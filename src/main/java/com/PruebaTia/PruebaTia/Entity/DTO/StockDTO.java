package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StockDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Integer cantidadDisponible;
}
