package com.example.sistemadegestiondecitasmedicas.repository;

import com.example.sistemadegestiondecitasmedicas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByPaciente(String paciente);

    List<Cita> findByDoctor(String doctor);

    Cita findByFechaAndHora(String fecha, String hora);

    boolean existsByDoctorAndFechaAndHora(String doctor, String fecha, String hora);
}