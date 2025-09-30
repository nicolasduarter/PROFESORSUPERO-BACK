package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
    List<Grupo> findByProfesor_Id(String profesor);
    List<Grupo> findBynombre(String nombre);
    List<Grupo> findByEstudiantes_id(String idGrupo);
    List<Grupo> findByMateria_ID(String idMateria);
}
