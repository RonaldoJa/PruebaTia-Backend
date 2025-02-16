package com.PruebaTia.PruebaTia.Controller;

import com.PruebaTia.PruebaTia.Entity.ResponseGeneric;
import com.PruebaTia.PruebaTia.Entity.User;
import com.PruebaTia.PruebaTia.Entity.UserRol;
import com.PruebaTia.PruebaTia.Services.ImplRolService;
import com.PruebaTia.PruebaTia.Services.ImplUserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController  {
   @Autowired
   private ImplUserServices userService;
   @Autowired
   private ImplRolService rolService;
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/save")
    public ResponseEntity<?> GuardarUsuario(@RequestBody User user) throws Exception {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRol> usuarioRoles = new HashSet<>();
        UserRol usuarioRol = new UserRol();
        usuarioRol.setRol(rolService.obtenerRol("ADMIN"));
        usuarioRol.setUsuario(user);
        usuarioRoles.add(usuarioRol);
        userService.guardarUsuario(user, usuarioRoles);
        ResponseGeneric<String> response = ResponseGeneric.success(null);

        return ResponseEntity.ok(response);
    }

    @GetMapping(" /(username}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public User obtenerUsuario(@PathVariable("username") String username) {
        return userService.ObtenerUsuario(username);
    }

    @GetMapping(" /(usuarioId}")
    public Boolean eliminarUserbyId(@PathVariable("usuarioId") Long userID) {
        return userService.eliminarUsuario(userID);
    }

}
