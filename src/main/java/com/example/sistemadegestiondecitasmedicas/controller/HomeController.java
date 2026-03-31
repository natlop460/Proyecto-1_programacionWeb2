package com.example.sistemadegestiondecitasmedicas.controller;


import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.misCitasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final misCitasService citaService;

    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String home(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");

        model.addAttribute("usuario", usuario);
        model.addAttribute("citas", citaService.obtenerCitasPorUsuario(usuario));

        return "dashboard";
    }
}
