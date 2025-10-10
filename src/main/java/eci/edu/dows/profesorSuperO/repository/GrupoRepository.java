package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
    List<Grupo> findByProfesor_Id(String profesor);
    List<Grupo> findByEstudiantes_id(String idGrupo);
    List<Grupo> findByMateria_id(String idMateria);
    List<Grupo> findByNombre(String  nombre);
    List<Grupo> findByMateria_IdAnd_cupo(String id,int cupo);
}
