package com.example.sistemadegestiondecitasmedicas.model;
import lombok.Data;

@Data
public class Usuario {

    private String nombre;
    private String email;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String password, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}
