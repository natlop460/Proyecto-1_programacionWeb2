package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    // Método principal de registro (mueve la lógica del controller aquí)
    public String registrar(String nombre, String email, String password, String rol) {

        if (esRolPrivilegiado(rol)) {
            emailService.enviarCorreoRegistro(
                    "lnathalie803@gmail.com", nombre, email, password, rol
            );
            return "PENDIENTE_APROBACION";
        }

        Usuario usuario = new Usuario(nombre, email, password, rol);
        usuarioRepository.save(usuario);

        return "REGISTRADO";
    }

    // Encapsula la lógica de roles
    private boolean esRolPrivilegiado(String rol) {
        return rol.equalsIgnoreCase("ADMIN") ||
                rol.equalsIgnoreCase("DOCTOR") ||
                rol.equalsIgnoreCase("Administrador") ||
                rol.equalsIgnoreCase("Doctor");
    }

    // Login (podría mejorarse con query en repository, pero lo dejamos claro)
    public Usuario login(String email, String password){
        for(Usuario u : usuarioRepository.findAll()){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    // Lógica movida correctamente al service
    public List<Usuario> obtenerDoctores() {
        List<Usuario> doctores = new ArrayList<>();

        for (Usuario u : usuarioRepository.findAll()) {
            if (u.getRol().equalsIgnoreCase("DOCTOR")) {
                doctores.add(u);
            }
        }

        return doctores;
    }
}
