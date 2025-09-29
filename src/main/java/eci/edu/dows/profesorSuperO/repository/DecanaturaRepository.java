package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Decanatura;
import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DecanaturaRepository extends MongoRepository<Decanatura, String> {
    Optional<Decanatura> findByFacultad(Facultades facultad);
}

