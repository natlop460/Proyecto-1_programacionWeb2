package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.service.CitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;


    @GetMapping("/citas")
    public String verCitas(Model model){

        model.addAttribute("citas", citaService.obtenerCitas());

        return "citas";
    }


    @GetMapping("/citas/crear")
    public String formularioCrearCita(Model model){

        model.addAttribute("cita", new Cita());

        return "crear-cita";
    }


    @PostMapping("/citas/guardar")
    public String guardarCita(@ModelAttribute Cita cita){

        citaService.guardarCita(cita);

        return "redirect:/citas";
    }

}