package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class misCitasService {

    private final CitaRepository citaRepository;

    // Método principal (usado por controller)
    public boolean guardarCita(Cita cita){

        if(!fechaEsValida(cita.getFecha())){
            return false;
        }

        if(citaExiste(cita.getDoctor(), cita.getFecha(), cita.getHora())){
            return false;
        }

        citaRepository.save(cita);
        return true;
    }

    // Método central para el dashboard
    public List<Cita> obtenerCitasPorUsuario(Usuario usuario){

        if (usuario.getRol().equalsIgnoreCase("USER")) {
            return obtenerCitasDePaciente(usuario);
        }

        if (usuario.getRol().equalsIgnoreCase("DOCTOR")) {
            return obtenerCitasDoctor(usuario.getNombre());
        }

        // ADMIN u otros → ven todas
        return obtenerTodasLasCitas();
    }

    // =========================
    // Métodos internos
    // =========================

    public List<Cita> obtenerTodasLasCitas(){
        return citaRepository.findAll();
    }

    private List<Cita> obtenerCitasDePaciente(Usuario usuario){
        List<Cita> citasUsuario = new ArrayList<>();

        for (Cita cita: citaRepository.findAll()){
            if (cita.getPaciente().equals(usuario.getNombre())){
                citasUsuario.add(cita);
            }
        }

        return citasUsuario;
    }

    public List<Cita> obtenerCitasDoctor(String doctor){
        List<Cita> citasDoctor = new ArrayList<>();

        for (Cita cita: citaRepository.findAll()){
            if (cita.getDoctor().equals(doctor)){
                citasDoctor.add(cita);
            }
        }

        return citasDoctor;
    }

    public Cita obtenerCitaPorFechaYHora(String fecha, String hora) {
        for (Cita cita : citaRepository.findAll()) {
            if (cita.getFecha().equals(fecha) && cita.getHora().equals(hora)) {
                return cita;
            }
        }
        return null;
    }

    private boolean fechaEsValida(String fecha){
        LocalDate fechaCita = LocalDate.parse(fecha);
        LocalDate hoy = LocalDate.now();
        return !fechaCita.isBefore(hoy);
    }

    private boolean citaExiste(String doctor, String fecha, String hora){
        for(Cita cita : citaRepository.findAll()){
            if(cita.getDoctor().equals(doctor) &&
                    cita.getFecha().equals(fecha) &&
                    cita.getHora().equals(hora)){
                return true;
            }
        }
        return false;
    }

    public void eliminarCita(String fecha, String hora, String doctor){
        citaRepository.delete(fecha, hora, doctor);
    }

    public Map<String, Cita> obtenerAgenda(String fecha){

        if (fecha == null) {
            fecha = LocalDate.now().toString();
        }

        List<String> horas = List.of(
                "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00",
                "08:00","09:00","10:00","11:00","12:00",
                "13:00","14:00","15:00","16:00","17:00","18:00",
                "19:00","20:00","21:00","22:00","23:00"
        );

        Map<String, Cita> agenda = new HashMap<>();

        for(String hora : horas){
            agenda.put(hora, obtenerCitaPorFechaYHora(fecha, hora));
        }
        return agenda;
    }
}
