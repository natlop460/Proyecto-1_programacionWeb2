package com.example.sistemadegestiondecitasmedicas.repository;

import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    List<Usuario> findByRolIgnoreCase(String rol);
}