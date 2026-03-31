package com.example.sistemadegestiondecitasmedicas.repository;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CitaRepository {

    private List<Cita> citas = new ArrayList<>();

    public void save(Cita cita){
        citas.add(cita);
    }

    public List<Cita> findAll(){
        return citas;
    }

    public void delete(String fecha, String hora, String doctor){
        citas.removeIf(cita ->
                cita.getFecha().equals(fecha) &&
                        cita.getHora().equals(hora) &&
                        cita.getDoctor().equals(doctor)
        );
    }
}
