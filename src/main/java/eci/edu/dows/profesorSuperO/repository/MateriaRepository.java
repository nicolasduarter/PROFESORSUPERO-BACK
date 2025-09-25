package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Materia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository extends MongoRepository<Materia, String>{
    List<Materia> findByEstado(String estado);
    List<Materia> findByNombre(String nombre);
}
