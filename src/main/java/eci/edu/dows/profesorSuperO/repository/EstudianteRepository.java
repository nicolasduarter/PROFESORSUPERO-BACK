package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    Optional<Estudiante> findByCorreo(String correo);
    List<Estudiante> findByFacultad(String facultad);
    List<Estudiante> findBySemestre(int semestre);
    List<Estudiante> findByGruposidGrupo(String idGrupo);

}