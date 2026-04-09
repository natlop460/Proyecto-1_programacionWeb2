package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.misCitasService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final misCitasService citaService;

    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String home(Model model, Authentication authentication){

        String email = authentication.getName();

        Usuario usuario = citaService.obtenerUsuarioPorEmail(email);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado en la base de datos");
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("citas", citaService.obtenerCitasPorUsuario(usuario));

        return "dashboard";
    }
}