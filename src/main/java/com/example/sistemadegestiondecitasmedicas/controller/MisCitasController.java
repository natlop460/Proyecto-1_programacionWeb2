package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.misCitasService;

import com.example.sistemadegestiondecitasmedicas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MisCitasController {

    @Autowired
    private misCitasService citaService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/misCitas")
    public String verCitas(Model model, HttpSession session){
        //Obtenemos los datos de session del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");
        //Usamos el model para enviar los datos del backend al html por medio del id citas y usuario
        model.addAttribute("usuario",usuario);
        if (usuario.getRol().toLowerCase().equals("doctor"))
            model.addAttribute("citas", citaService.obtenerCitasDoctor(usuario.getNombre()));
        else if(usuario.getRol().toLowerCase().equals("user"))
            model.addAttribute("citas", citaService.obtenerCitas(usuario));
        else
            model.addAttribute("citas", citaService.obtenerCitas());
        return "misCitas";
    }


    @GetMapping("/misCitas/crear")
    public String formularioCrearCita(Model model, HttpSession session){
        //Obtenemos los datos de session del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");
        //Usamos el model para enviar los datos del backend al html por medio del id citas y usuario
        model.addAttribute("usuario",usuario);
        model.addAttribute("cita", new Cita());
        List<Usuario> doctores = usuarioService.obtenerDoctores();
        model.addAttribute("doctores", doctores);
        return "crear-cita";
    }

    @GetMapping("/agenda")
    public String verAgenda(
            @RequestParam(required = false) String fecha,
            Model model) {
        if (fecha == null) {
            fecha = LocalDate.now().toString();
        }
        List<String> horas = List.of(
                "00:00","01:00","02:00","03:00", "04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00",
                "13:00","14:00","15:00","16:00", "17:00","18:00","19:00","20:00","21:00","22:00","23:00"
        );
        Map<String, Cita> agenda = new HashMap<>();
        for(String hora : horas){
            agenda.put(hora, citaService.obtenerCitaPorFechaYHora(fecha, hora));
        }
        model.addAttribute("agenda", agenda);
        model.addAttribute("fecha", fecha);
        return "agenda";
    }


    @PostMapping("/citas/guardar")
    public String guardarCita(@ModelAttribute Cita cita, RedirectAttributes redirectAttributes){
        if(!citaService.fechaEsValida(cita.getFecha())){
            redirectAttributes.addFlashAttribute("mensaje",
                    "No se pueden crear citas en fechas pasadas");
            return "redirect:/misCitas";
        }
        if(citaService.citaExiste(cita.getDoctor(), cita.getFecha(), cita.getHora())){
            redirectAttributes.addFlashAttribute("mensaje",
                    "Este espacio ya se encuentra ocupado, vuelva a intentar en otro horario");
            return "redirect:/misCitas";
        }
        citaService.guardarCita(cita);
        redirectAttributes.addFlashAttribute("mensaje",
                "Cita creada correctamente");
        return "redirect:/misCitas";
    }


    @PostMapping("/citas/eliminar")
    public String eliminarCita(
            @RequestParam String fecha,
            @RequestParam String hora,
            @RequestParam String doctor,
            RedirectAttributes redirectAttributes){
        citaService.eliminarCita(fecha, hora, doctor);
        redirectAttributes.addFlashAttribute("mensaje",
                "Cita eliminada correctamente");

        return "redirect:/misCitas";
    }
}