package com.example.sistemadegestiondecitasmedicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRegistro(String correo, String nombre, String email, String password, String rol) {

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setFrom("natalie.lopez12@hotmail.com"); // MUY IMPORTANTE
        mensaje.setTo(correo);
        mensaje.setSubject("Solicitud de registro");

        mensaje.setText(
                "Hola " + nombre +
                        ", su solicitud para registrarse como " + rol +
                        ". El correo electrónico es " + email +
                        " y la contraseña es " + password
        );

        mailSender.send(mensaje);
    }
}