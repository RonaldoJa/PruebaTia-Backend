package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.DTO.LocalDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.ProductDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.StockDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.SupplierDTO;
import com.PruebaTia.PruebaTia.Entity.Local;
import com.PruebaTia.PruebaTia.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public Local crearLocal(Local local) {
        local.setFechaCreacion(LocalDateTime.now());
        return localRepository.save(local);
    }

    public List<LocalDTO> listarLocales() {

        return localRepository.findAll().stream().map(local -> {
            LocalDTO dto = new LocalDTO();
            dto.setId(local.getId());
            dto.setNombre(local.getNombre());
            dto.setDireccion(local.getDireccion());
            dto.setTelefono(local.getTelefono());
            dto.setTipoLocal(local.getTipoLocal());
            dto.setEstado(local.getEstado());
            dto.setFechaCreacion(local.getFechaCreacion());


            return dto;
        }).collect(Collectors.toList());
    }

    public Local obtenerLocal(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local no encontrado"));
    }

    public Local actualizarLocal(Long id, Local local) {
        Local localExistente = obtenerLocal(id);
        localExistente.setNombre(local.getNombre());
        localExistente.setDireccion(local.getDireccion());
        localExistente.setTelefono(local.getTelefono());
        localExistente.setTipoLocal(local.getTipoLocal());
        localExistente.setEstado(local.getEstado());
        return localRepository.save(localExistente);
    }

    public void eliminarLocal(Long id) {
        localRepository.deleteById(id);
    }
}