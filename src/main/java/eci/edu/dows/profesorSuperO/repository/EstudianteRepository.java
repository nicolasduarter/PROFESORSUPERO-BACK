package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Facultad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    List<Estudiante> findByFacultad(Facultad facultad);
    List<Estudiante> findBySemestre(int semestre);
}