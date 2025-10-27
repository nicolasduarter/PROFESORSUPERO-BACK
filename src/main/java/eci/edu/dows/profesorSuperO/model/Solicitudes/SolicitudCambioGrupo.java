package eci.edu.dows.profesorSuperO.model.Solicitudes;

import eci.edu.dows.profesorSuperO.model.Enums.TipoSolicitud;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@TypeAlias("solicitudCambioGrupo")
public class SolicitudCambioGrupo extends Solicitud {
    private String materiaProblemaId;
    private String grupoId;
    private String grupoCambioId;

    public SolicitudCambioGrupo(String id, String estudianteId, String facultadId,String motivo, LocalDate fecha,
                                String materiaProblema, String grupo, String grupoCambio) {
        super(id, estudianteId, facultadId,motivo, fecha);
        this.materiaProblemaId = materiaProblema;
        this.grupoId = grupo;
        this.grupoCambioId = grupoCambio;
        this.tipoSolicitud = TipoSolicitud.CAMBIO_GRUPO;

    }
}
