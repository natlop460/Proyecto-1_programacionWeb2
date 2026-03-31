package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.misCitasService;
import com.example.sistemadegestiondecitasmedicas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MisCitasController {

    private final misCitasService citaService;
    private final UsuarioService usuarioService;

    @GetMapping("/misCitas")
    public String verCitas(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("citas", citaService.obtenerCitasPorUsuario(usuario));

        return "misCitas";
    }

    @GetMapping("/misCitas/crear")
    public String formularioCrearCita(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("cita", new Cita());
        model.addAttribute("doctores", usuarioService.obtenerDoctores());

        return "crear-cita";
    }

    @GetMapping("/agenda")
    public String verAgenda(
            @RequestParam(required = false) String fecha,
            Model model) {

        Map<String, Cita> agenda = citaService.obtenerAgenda(fecha);

        model.addAttribute("agenda", agenda);
        model.addAttribute("fecha", fecha == null ? java.time.LocalDate.now().toString() : fecha);

        return "agenda";
    }

    @PostMapping("/citas/guardar")
    public String guardarCita(@ModelAttribute Cita cita,
                              RedirectAttributes redirectAttributes){

        boolean creada = citaService.guardarCita(cita);

        if (!creada) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "Error: fecha inválida o espacio ocupado");
            return "redirect:/misCitas";
        }

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
