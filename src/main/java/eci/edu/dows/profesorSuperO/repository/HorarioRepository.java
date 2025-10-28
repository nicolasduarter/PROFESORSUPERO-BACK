package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Horario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends MongoRepository<Horario, String> {
}
