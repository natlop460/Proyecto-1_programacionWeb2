package com.example.sistemadegestiondecitasmedicas.graphql;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import com.example.sistemadegestiondecitasmedicas.service.misCitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CitaGraphQLController {

    @Autowired
    private misCitasService citaService;

    @QueryMapping
    public List<Cita> obtenerCitas() {
        return citaService.obtenerTodasLasCitas();
    }
}

