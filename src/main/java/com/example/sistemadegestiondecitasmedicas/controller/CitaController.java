package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.CitaService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;


    @GetMapping("/citas")
    public String verCitas(Model model, HttpSession session){
        //Obtenemos los datos de session del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");
        //Usamos el model para enviar los datos del backend al html por medio del id citas y usuario
        model.addAttribute("usuario",usuario);
        model.addAttribute("citas", citaService.obtenerCitas(usuario));

        return "citas";
    }


    @GetMapping("/citas/crear")
    public String formularioCrearCita(Model model, HttpSession session){
        //Obtenemos los datos de session del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");
        //Usamos el model para enviar los datos del backend al html por medio del id citas y usuario
        model.addAttribute("usuario",usuario);
        model.addAttribute("cita", new Cita());

        return "crear-cita";
    }


    @PostMapping("/citas/guardar")
    public String guardarCita(@ModelAttribute Cita cita, RedirectAttributes redirectAttributes){

        citaService.guardarCita(cita);
        redirectAttributes.addFlashAttribute("mensaje", "Cita creada correctamente");
        return "redirect:/dashboard";
    }

}