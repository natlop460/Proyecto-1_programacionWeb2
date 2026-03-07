package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

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
}