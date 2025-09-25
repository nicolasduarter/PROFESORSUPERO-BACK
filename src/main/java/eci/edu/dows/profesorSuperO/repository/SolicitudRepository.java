package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SolicitudRepository extends MongoRepository<Solicitud, String>{
    List<Solicitud> findByEstudiante_Id(String id);
    List<Solicitud> findByEstudiante_facultad(Facultades facultadd);
    List<Solicitud> findByEstado(EstadoSolicitud estado);
    List<Solicitud> findByFecha(LocalDate fecha);
    List<Solicitud> findByPrioridad(int prioridad);
}
