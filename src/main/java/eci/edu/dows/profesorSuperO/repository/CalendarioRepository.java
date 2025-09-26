package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.CalendarioAcademico;
import eci.edu.dows.profesorSuperO.model.Clase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CalendarioRepository  extends MongoRepository<CalendarioAcademico, String> {
    Optional<CalendarioAcademico> findTopByOrderByStartDesc();

}
