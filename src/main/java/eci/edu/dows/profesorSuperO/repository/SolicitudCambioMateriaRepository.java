package eci.edu.dows.profesorSuperO.repository;

import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Solicitudes.SolicitudCambioMateria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SolicitudCambioMateriaRepository extends MongoRepository<SolicitudCambioMateria, String> {
    List<SolicitudCambioMateria> findByMateriaCambio(Materia materia);
    List<SolicitudCambioMateria> findByMateriaProblema(Materia materia);
}
