package com.PruebaTia.PruebaTia.Entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SupplierDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;
}
