package com.floristeria.floristeria.controller;

import com.floristeria.floristeria.domain.Usuario;
import com.floristeria.floristeria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registerUserAccount(@ModelAttribute("usuario") @Valid Usuario usuario, 
                                      BindingResult result, 
                                      Model model, 
                                      RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "registro";
        }
        
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuario.setActivo(true);
            usuarioService.save(usuario);
        } catch (Exception e) {
            model.addAttribute("registrationError", "Ya existe un usuario con ese nombre de usuario.");
            return "registro";
        }
        
        redirectAttributes.addFlashAttribute("registrationSuccess", "Registro exitoso. Por favor, inicia sesión.");
        return "redirect:/login";
    }
}