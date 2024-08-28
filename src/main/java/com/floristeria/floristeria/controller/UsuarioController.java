package com.floristeria.floristeria.controller;

import com.floristeria.floristeria.domain.Usuario;
import com.floristeria.floristeria.domain.Rol;
import com.floristeria.floristeria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/perfil")
    public String perfil(Model model) {
        return "perfil";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario, Model model) {
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            model.addAttribute("error", "El campo 'username' no puede estar vacío");
            return "/usuario/modifica";
        }

        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            Usuario usuarioExistente = usuarioService.getUsuario(usuario);
            if (usuarioExistente != null) {
                usuario.setPassword(usuarioExistente.getPassword());
            }
        }

        // Mantener la lista de roles existentes o asignar un rol por defecto si es un nuevo usuario
        if (usuario.getIdUsuario() == null) {
            usuarioService.save(usuario);
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");  // Rol por defecto
            rol.setUsuario(usuario);
            usuario.getRoles().add(rol);
        } else {
            Usuario usuarioExistente = usuarioService.getUsuario(usuario);
            if (usuarioExistente != null && usuarioExistente.getRoles() != null) {
                usuario.setRoles(usuarioExistente.getRoles());
            }
        }

        usuarioService.save(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(@PathVariable("idUsuario") Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(@PathVariable("idUsuario") Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }

    @GetMapping("/modificarRol/{idUsuario}")
    public String modificarRol(@PathVariable("idUsuario") Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);

        var rolesDisponibles = List.of("ROLE_ADMIN", "ROLE_VENDEDOR", "ROLE_USER");
        model.addAttribute("rolesDisponibles", rolesDisponibles);

        return "/usuario/modificaRol";
    }

    @PostMapping("/guardarRol")
    public String guardarRol(Usuario usuario, @RequestParam("rol") String rol) {
        Usuario usuarioExistente = usuarioService.getUsuario(usuario);

        if (usuarioExistente != null) {
            if (usuarioExistente.getRoles() == null) {
                usuarioExistente.setRoles(new ArrayList<>());
            }

            usuarioExistente.getRoles().clear();

            Rol nuevoRol = new Rol();
            nuevoRol.setNombre(rol);
            nuevoRol.setUsuario(usuarioExistente);
            usuarioExistente.getRoles().add(nuevoRol);

            usuarioService.save(usuarioExistente);
        }

        return "redirect:/usuario/listado";
    }
}
