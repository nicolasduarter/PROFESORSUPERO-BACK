package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Facultades;
import eci.edu.dows.profesorSuperO.model.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface ProfesorRepository extends MongoRepository<Profesor, String>{
}
