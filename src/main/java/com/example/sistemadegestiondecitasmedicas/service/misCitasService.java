package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class misCitasService {

    private List<Cita> citas = new ArrayList<>();

    public boolean guardarCita(Cita cita){
        if(!fechaEsValida(cita.getFecha())){
            return false;
        }
        if(citaExiste(cita.getDoctor(), cita.getFecha(), cita.getHora())){
            return false;
        }
        citas.add(cita);
        return true;
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


    public boolean fechaEsValida(String fecha){
        LocalDate fechaCita = LocalDate.parse(fecha);
        LocalDate hoy = LocalDate.now();
        return !fechaCita.isBefore(hoy);
    }

    public boolean citaExiste(String doctor, String fecha, String hora){
        for(Cita cita : citas){
            if(cita.getDoctor().equals(doctor) &&
                    cita.getFecha().equals(fecha) &&
                    cita.getHora().equals(hora)){
                return true;
            }
        }
        return false;
    }

    public void eliminarCita(String fecha, String hora, String doctor){
        citas.removeIf(cita ->
                cita.getFecha().equals(fecha) &&
                        cita.getHora().equals(hora) &&
                        cita.getDoctor().equals(doctor)
        );
    }

}