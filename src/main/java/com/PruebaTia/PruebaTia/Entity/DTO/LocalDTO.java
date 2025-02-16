package com.PruebaTia.PruebaTia.Entity.DTO;

import com.PruebaTia.PruebaTia.Entity.Enum.TipoLocal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String direccion;
    @Getter
    @Setter
    private String telefono;
    @Getter
    @Setter
    private TipoLocal tipoLocal;
    @Getter
    @Setter
    private Boolean estado;
    @Getter
    @Setter
    private LocalDateTime fechaCreacion;
}
