package com.PruebaTia.PruebaTia.Entity.DTO;

import com.PruebaTia.PruebaTia.Entity.Enum.TipoCliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ClientDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String correo;
    @Getter
    @Setter
    private String telefono;
    @Getter
    @Setter
    private TipoCliente tipoCliente;
    @Getter
    @Setter
    private LocalDateTime fechaRegistro;
}
