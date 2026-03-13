package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class misCitasService {

    private List<Cita> citas = new ArrayList<>();

    public void guardarCita(Cita cita){
        cita.setEstado("Confirmada");
        citas.add(cita);
    }

    public List<Cita> obtenerCitas(){
        return citas;
    }


    public List<Cita> obtenerCitas(Usuario usuario){
        List<Cita> citasUsuario = new ArrayList<>();
        for (Cita cita: citas){
            if (cita.getPaciente().equals(usuario.getNombre())){
                citasUsuario.add(cita);
            }
        }
        return citasUsuario;
    }

    public List<Cita> obtenerCitasDoctor(String Doctor){
        List<Cita> citasDoctor = new ArrayList<>();
        for (Cita cita: citas){
            if (cita.getDoctor().equals(Doctor)){
                citasDoctor.add(cita);
            }
        }
        return citasDoctor;
    }

    public Cita obtenerCitaPorFechaYHora(String fecha, String hora) {
        for (Cita cita : citas) {
            if (cita.getFecha().equals(fecha) && cita.getHora().equals(hora)) {
                return cita;
            }
        }
        return null;
    }


}