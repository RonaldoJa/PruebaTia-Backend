package com.PruebaTia.PruebaTia.Services;


import com.PruebaTia.PruebaTia.Entity.Client;
import com.PruebaTia.PruebaTia.Entity.DTO.ClientDTO;
import com.PruebaTia.PruebaTia.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client saveClient(Client client){
        client.setFechaRegistro(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public List<ClientDTO> listClients() {
        List<Client> clientes = clientRepository.findAll();
        return clientes.stream()
                .map(this::mapToClientDTO)
                .collect(Collectors.toList());
    }

    public Client getClientForId(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Optional<Client> actualizarCliente(Long id, Client clienteActualizado) {
        return clientRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setCorreo(clienteActualizado.getCorreo());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setTipoCliente(clienteActualizado.getTipoCliente());
                    return clientRepository.save(cliente);
                });
    }

    // Eliminar cliente
    public boolean eliminarCliente(Long id) {
        return clientRepository.findById(id)
                .map(cliente -> {
                    clientRepository.delete(cliente);
                    return true;
                }).orElse(false);
    }

    private ClientDTO mapToClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNombre(client.getNombre());
        dto.setCorreo(client.getCorreo());
        dto.setTelefono(client.getTelefono());
        dto.setTipoCliente(client.getTipoCliente());
        dto.setFechaRegistro(client.getFechaRegistro());
        return dto;
    }
}
