package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Facultad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultadRepository  extends MongoRepository<Facultad, String> {

}
