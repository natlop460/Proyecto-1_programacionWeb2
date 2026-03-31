package com.example.sistemadegestiondecitasmedicas.repository;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {
        // Datos en memoria
        usuarios.add(new Usuario("admin","lnathalie803@gmail.com", "123", "Administrador"));
        usuarios.add(new Usuario("Cindy","Dra@gmail.com", "123", "Doctor"));
        usuarios.add(new Usuario("Mauricio","Dr@gmail.com", "123", "Doctor"));
    }

    public void save(Usuario usuario){
        usuarios.add(usuario);
    }

    public List<Usuario> findAll(){
        return usuarios;
    }
}
