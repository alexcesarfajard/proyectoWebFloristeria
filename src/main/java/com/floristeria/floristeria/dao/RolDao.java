package com.floristeria.floristeria.dao;

import com.floristeria.floristeria.domain.Rol;
import com.floristeria.floristeria.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Long> {

    // Método para eliminar todos los roles de un usuario específico
    void deleteByUsuario(Usuario usuario);

    // Opcionalmente, puedes agregar más métodos personalizados si los necesitas
    // List<Rol> findByUsuario(Usuario usuario);
}