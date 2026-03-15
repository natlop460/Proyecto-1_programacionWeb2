package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.EmailService;
import com.example.sistemadegestiondecitasmedicas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private EmailService emailService;

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String rol,
            RedirectAttributes redirectAttributes
    ){
        if (rol.equals("Administrador") || rol.equals("ADMIN")  || rol.equals("Doctor") || rol.equals("DOCTOR") ){
            emailService.enviarCorreoRegistro("lnathalie803@gmail.com", nombre, email, password, rol);
            redirectAttributes.addFlashAttribute("mensaje", "Se ha enviado un correo para aprobación.");
        }else{
            Usuario usuario = new Usuario(nombre, email, password, rol);
            usuarioService.registrarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario creado con éxito.");

        }
        return "redirect:/";
    }

    @PostMapping("/dashboard")
    public String dashboard(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        Usuario usuario = usuarioService.login(email,password);
        if(usuario != null){
            //Se guarda el usuario con el rol
            session.setAttribute("usuariologueado",usuario);
            return "redirect:/dashboard";
        }
        else{
            redirectAttributes.addFlashAttribute("mensajeError", "Usuario invalido.");
            return "redirect:/";
        }
    }
}