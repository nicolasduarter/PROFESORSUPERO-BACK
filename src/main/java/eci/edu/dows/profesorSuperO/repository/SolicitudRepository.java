package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Solicitudes.Solicitud;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SolicitudRepository extends MongoRepository<Solicitud, String>{
    List<Solicitud> findByEstudianteId(String id);
    List<Solicitud> findByFacultad(Facultad facultad);
    List<Solicitud> findByEstado(EstadoSolicitud estado);
    List<Solicitud> findByFecha(LocalDate fecha);
    List<Solicitud> findByPrioridad(int prioridad);
}
