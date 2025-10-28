package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Usuarios.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProfesorRepository extends MongoRepository<Profesor, String>{
}
