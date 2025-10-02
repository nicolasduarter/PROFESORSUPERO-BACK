package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.TipoSolicitud;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValidaCambioGrupo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Getter
@Setter
@TypeAlias("solicitudCambioGrupo")
public class SolicitudCambioGrupo extends Solicitud {
    @DBRef
    private Materia materiaProblema;
    private Grupo grupo;
    private Grupo grupoCambio;

    public SolicitudCambioGrupo(String id, Estudiante estudiante, String motivo, LocalDate fecha,
                                Materia materiaProblema, Grupo grupo, Grupo grupoCambio) {
        super(id, estudiante, motivo, fecha);
        this.materiaProblema = materiaProblema;
        this.grupo = grupo;
        this.grupoCambio = grupoCambio;
        this.tipoSolicitud = TipoSolicitud.CAMBIO_GRUPO;

    }


}
