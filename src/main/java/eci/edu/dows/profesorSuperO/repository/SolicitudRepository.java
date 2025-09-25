package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SolicitudRepository extends MongoRepository<Solicitud, String>{
    List<Solicitud> findByEstudiante_Id(String id);
    List<Solicitud> findByEstudiante_facultad(Facultades facultadd);
    List<Solicitud> findByEstado(EstadoSolicitud estado);
    List<Solicitud> findByMateriaProblema(Materia materia);
    List<Solicitud> findByMateriaCambio(Materia materia);
    List<Solicitud> findByGrupo(Grupo grupo);
    List<Solicitud> findByGrupoCambio(Grupo grupo);
    List<Solicitud> findByFecha(String fecha);
    List<Solicitud> findByPrioridad(int prioridad);
}
