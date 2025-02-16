package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.DTO.LocalDTO;
import com.PruebaTia.PruebaTia.Entity.Local;
import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/locales")
@CrossOrigin(origins = "http://localhost:5173")
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<?> crearLocal(@RequestBody Local local) {
        Local nuevoLocal = localService.crearLocal(local);
        ResponseGeneric<Local> response = ResponseGeneric.success(nuevoLocal);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> listarLocales() {
        ResponseGeneric<List<LocalDTO>> response = ResponseGeneric.success(localService.listarLocales());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLocal(@PathVariable Long id) {
        ResponseGeneric<Local> response = ResponseGeneric.success(localService.obtenerLocal(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLocal(@PathVariable Long id, @RequestBody Local local) {
        localService.actualizarLocal(id, local);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLocal(@PathVariable Long id) {
        localService.eliminarLocal(id);
        ResponseGeneric<String> response = ResponseGeneric.success(null);
        return ResponseEntity.ok(response);
    }
}