package com.example.sistemadegestiondecitasmedicas.model;
import lombok.Data;

@Data
public class Cita {


    private Long id;
    private String paciente;
    private String fecha;
    private String hora;
    private String doctor;
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(Long id, String paciente,String fecha, String hora, String doctor, String motivo, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.motivo = motivo;
        this.estado = estado;
    }
}