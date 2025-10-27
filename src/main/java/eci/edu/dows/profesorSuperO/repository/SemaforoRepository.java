package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Semaforo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SemaforoRepository extends MongoRepository<Semaforo, String> {
}
