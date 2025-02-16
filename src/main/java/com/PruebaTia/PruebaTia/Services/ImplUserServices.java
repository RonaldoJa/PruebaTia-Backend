package com.PruebaTia.PruebaTia.Services;

import com.PruebaTia.PruebaTia.Entity.User;
import com.PruebaTia.PruebaTia.Entity.UserRol;
import com.PruebaTia.PruebaTia.Repository.RolRepository;
import com.PruebaTia.PruebaTia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class ImplUserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;

    public User guardarUsuario(User usuario, Set<UserRol> userRolSet) throws Exception {
        User user= userRepository.findByUsername(usuario.getUsername());
        if(user!=null)
        {
            System.out.println("El usuario ya exite");
            throw  new Exception("El usuario ya existe");
        }else {
            for(UserRol usuarioRol:userRolSet)
            {
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUserRolSet().addAll(userRolSet);
            user = userRepository.save(usuario);
        }
        return user;
    }


    public User ObtenerUsuario(String username) {
        return  userRepository.findByUsername(username);
    }

    public Boolean eliminarUsuario(Long usuarioId) {
        userRepository.deleteById(usuarioId);
        return userRepository.existsById(usuarioId);
    }


    public List<User> ListUser() {
        List<User>  list= userRepository.findAll();
        return list;
    }
}
