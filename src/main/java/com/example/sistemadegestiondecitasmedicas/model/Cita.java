package com.example.sistemadegestiondecitasmedicas.model;

public class Cita {

    private Long id;
    private String fecha;
    private String hora;
    private String doctor;
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(Long id, String fecha, String hora, String doctor, String motivo, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.motivo = motivo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}