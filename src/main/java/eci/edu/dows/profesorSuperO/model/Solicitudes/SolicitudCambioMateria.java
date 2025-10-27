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
@TypeAlias("solicitudCambioMateria")
public class SolicitudCambioMateria extends Solicitud {
    private String materiaProblemaId;
    private String materiaCambioId;
    private String grupoId;
    private String grupoCambioId;

    public SolicitudCambioMateria(String id, String estudianteId, String facultadId, String motivo, LocalDate fecha,
                                  String materiaProblemaId, String materiaCambioId, String grupoId, String grupoCambioId) {
        super(id, estudianteId, facultadId, motivo, fecha);
        this.materiaProblemaId = materiaProblemaId;
        this.materiaCambioId = materiaCambioId;
        this.grupoId = grupoId;
        this.grupoCambioId = grupoCambioId;
        this.tipoSolicitud = TipoSolicitud.CAMBIO_MATERIA;
    }

}
