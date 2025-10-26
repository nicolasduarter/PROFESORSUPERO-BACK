package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    List<Estudiante> findByFacultadFacultadName(Facultades facultad);
    List<Estudiante> findBySemestre(int semestre);
}