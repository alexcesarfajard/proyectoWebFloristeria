package com.floristeria.floristeria.service.impl;

import com.floristeria.floristeria.dao.UsuarioDao;
import com.floristeria.floristeria.dao.RolDao;
import com.floristeria.floristeria.domain.Rol;
import com.floristeria.floristeria.domain.Usuario;
import com.floristeria.floristeria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuario = usuarioDao.save(usuario);
        // Si el usuario es nuevo o no tiene roles, asignar el rol por defecto "USER"
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setUsuario(usuario);
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        Usuario usuarioToDelete = usuarioDao.findById(usuario.getIdUsuario()).orElse(null);

        if (usuarioToDelete != null) {
            // Eliminar los roles asociados al usuario
            List<Rol> roles = usuarioToDelete.getRoles();
            if (roles != null && !roles.isEmpty()) {
                for (Rol rol : roles) {
                    rolDao.delete(rol);
                }
            }
            // Luego, eliminar el usuario
            usuarioDao.delete(usuarioToDelete);
        }
    }

    @Override
    @Transactional
    public void updateRoles(Usuario usuario, List<Rol> nuevosRoles) {
        // Eliminar roles actuales
        rolDao.deleteByUsuario(usuario);
        // Asignar nuevos roles
        for (Rol rol : nuevosRoles) {
            rol.setUsuario(usuario);
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
        
}
}