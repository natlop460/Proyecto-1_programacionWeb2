package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String rol
    ){

        Usuario usuario = new Usuario(nombre, email, password, rol);
        usuarioService.registrarUsuario(usuario);

        return "redirect:/";
    }

    @PostMapping("/dashboard")
    public String dashboard(
            @RequestParam String email,
            @RequestParam String password
    ){

        Usuario usuario = usuarioService.login(email,password);

        if(usuario != null){
            return "redirect:/dashboard";
        }

        return "redirect:/";
    }
}