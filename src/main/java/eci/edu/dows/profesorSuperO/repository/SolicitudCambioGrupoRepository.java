package eci.edu.dows.profesorSuperO.repository;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.SolicitudCambioGrupo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface SolicitudCambioGrupoRepository extends MongoRepository<SolicitudCambioGrupo, String> {
    List<SolicitudCambioGrupo> findByGrupo(Grupo grupo);
    List<SolicitudCambioGrupo> findByGrupoCambio(Grupo grupo);
}
