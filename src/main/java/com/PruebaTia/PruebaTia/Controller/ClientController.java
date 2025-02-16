package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.Client;
import com.PruebaTia.PruebaTia.Entity.DTO.ClientDTO;
import com.PruebaTia.PruebaTia.Entity.DTO.DashBoardStats;
import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Repository.ClientRepository;
import com.PruebaTia.PruebaTia.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Client cliente) {
        cliente.setFechaRegistro(LocalDateTime.now());
        Client nuevoCliente = clientService.saveClient(cliente);
        ResponseGeneric<Client> response = ResponseGeneric.success(nuevoCliente);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<?> obtenerTodosLosClientes() {
        ResponseGeneric<List<ClientDTO>> response = ResponseGeneric.success(clientService.listClients());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        ResponseGeneric<Client> response = ResponseGeneric.success(clientService.getClientForId(id));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Client clienteActualizado) {
        clientService.actualizarCliente(id, clienteActualizado);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarCliente(@PathVariable Long id) {
        clientService.eliminarCliente(id);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }
}
