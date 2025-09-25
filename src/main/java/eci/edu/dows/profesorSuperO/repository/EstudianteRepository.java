package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    List<Estudiante> findByFacultad(String facultad);
    List<Estudiante> findBySemestre(int semestre);
    Optional<Estudiante> findByGrupos_IdGrupo(String idGrupo);

}