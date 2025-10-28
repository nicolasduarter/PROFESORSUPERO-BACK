package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Usuarios.Decanatura;
import eci.edu.dows.profesorSuperO.model.Facultad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DecanaturaRepository extends MongoRepository<Decanatura, String> {
    Optional<Decanatura> findByFacultad(Facultad facultad);
    Optional<Decanatura> findByFacultad_Id(String facultadId);

}

