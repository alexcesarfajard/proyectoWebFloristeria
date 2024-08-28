package com.floristeria.floristeria.service;

import com.floristeria.floristeria.domain.Rol;
import com.floristeria.floristeria.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario getUsuario(Usuario usuario);
    Usuario getUsuarioPorUsername(String username); // Método adicional para buscar usuario por nombre de usuario
    void save(Usuario usuario);
    void delete(Usuario usuario);
    
    // Nuevo método para actualizar roles
    void updateRoles(Usuario usuario, List<Rol> nuevosRoles);
    
}