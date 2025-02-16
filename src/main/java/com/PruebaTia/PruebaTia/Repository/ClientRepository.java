package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByTipoCliente(String tipoCliente);

    List<Client> findByNombreContaining(String nombre);
}
