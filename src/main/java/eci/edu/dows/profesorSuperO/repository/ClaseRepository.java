package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Clase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClaseRepository extends MongoRepository<Clase, String> {
    List<Clase> findByProfesor_Id(String profesor);
    List<Clase> findBySalon(String salon);
    List<Clase> findByFranjaHoraria(String hora);
    List<Clase> findByidClase(String nombre);
    List<Clase> findByidClaseAndEstado(String nombre, String estado);
    List<Clase> findByEstudiantes_id(String idGrupo);
}
