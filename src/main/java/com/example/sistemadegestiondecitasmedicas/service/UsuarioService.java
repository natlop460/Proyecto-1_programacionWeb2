package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {

        // Usuario creado en memoria

        usuarios.add(new Usuario("admin","lnathalie803@gmail.com", "123", "Administrador"));
        usuarios.add(new Usuario("Cindy","Dra@gmail.com", "123", "Doctor"));
        usuarios.add(new Usuario("Mauricio","Dr@gmail.com", "123", "Doctor"));

    }
    public void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public Usuario login(String email, String password){

        for(Usuario u : usuarios){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return u;
            }
        }

        return null;
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public List<Usuario> obtenerDoctores() {
        List<Usuario> doctores = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getRol().equalsIgnoreCase("DOCTOR")) {
                doctores.add(u);
            }
        }

        return doctores;
    }
}