package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Rol;
import com.PruebaTia.PruebaTia.Entity.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombreRol);


}
