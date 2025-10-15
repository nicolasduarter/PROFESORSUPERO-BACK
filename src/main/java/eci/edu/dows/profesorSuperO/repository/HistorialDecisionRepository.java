package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.HistorialDecision;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistorialDecisionRepository extends MongoRepository<HistorialDecision, String> {
    List<HistorialDecision> findBySolicitudIdOrderByFechaDecisionDesc(String solicitudId);
}