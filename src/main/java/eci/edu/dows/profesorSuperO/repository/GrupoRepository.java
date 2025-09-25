package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
    List<Grupo> findByProfesor_Id(String profesor);
    List<Grupo> findBySalon(String salon);
    List<Grupo> findByFranjaHoraria(String hora);
    List<Grupo> findBynombre(String nombre);
    List<Grupo> findBynombreAndEstado(String nombre, String estado);
    List<Grupo> findByEstudiantes_id(String idGrupo);
}
