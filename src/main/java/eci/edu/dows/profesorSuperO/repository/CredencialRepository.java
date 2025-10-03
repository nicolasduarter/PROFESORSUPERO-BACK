package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Credencial;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredencialRepository extends MongoRepository<Credencial, String> {
    Optional<Credencial> findByUsuario(String usuario);
}