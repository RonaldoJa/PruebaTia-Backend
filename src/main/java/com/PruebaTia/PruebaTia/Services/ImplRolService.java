package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.Rol;
import com.PruebaTia.PruebaTia.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplRolService {
    @Autowired
    private RolRepository rolRepository;

    public Rol obtenerRol(String nombreRol) {
        Rol rol= rolRepository.findByNombre(nombreRol);
        return rol;
    }
}
