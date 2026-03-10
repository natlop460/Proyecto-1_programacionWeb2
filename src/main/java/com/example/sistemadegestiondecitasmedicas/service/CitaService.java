package com.example.sistemadegestiondecitasmedicas.service;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService {

    private List<Cita> citas = new ArrayList<>();

    public void guardarCita(Cita cita){
        cita.setEstado("Confirmada");
        citas.add(cita);
    }

    public List<Cita> obtenerCitas(){
        return citas;
    }

}