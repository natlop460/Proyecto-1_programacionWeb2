package com.example.sistemadegestiondecitasmedicas.controller;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public String register(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String rol,
            RedirectAttributes redirectAttributes
    ){

        String resultado = usuarioService.registrar(nombre, email, password, rol);

        if (resultado.equals("PENDIENTE_APROBACION")) {
            redirectAttributes.addFlashAttribute("mensaje", "Se ha enviado un correo para aprobación.");
        } else {
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
            session.setAttribute("usuariologueado",usuario);
            return "redirect:/dashboard";
        }

        redirectAttributes.addFlashAttribute("mensajeError", "Usuario invalido.");
        return "redirect:/";
    }
}
