package com.example.sistemadegestiondecitasmedicas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;
    private String fecha;
    private String hora;
    private String doctor;
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(String paciente, String fecha, String hora, String doctor, String motivo, String estado) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.motivo = motivo;
        this.estado = estado;
    }
}