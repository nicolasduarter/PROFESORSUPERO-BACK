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
public class SolicitudCambioMateria extends  Solicitud {
    @DBRef
    private Materia materiaProblema;
    @DBRef
    private Materia materiaCambio;
    private Grupo grupo;
    private Grupo grupoCambio;

    public SolicitudCambioMateria(String id, Estudiante estudiante, String motivo, LocalDate fecha,
                                  Materia materiaProblema, Materia materiaCambio, Grupo grupo, Grupo grupoCambio) {
        super(id, estudiante, motivo, fecha);
        this.materiaProblema = materiaProblema;
        this.materiaCambio = materiaCambio;
        this.grupo = grupo;
        this.grupoCambio = grupoCambio;
        this.tipoSolicitud = TipoSolicitud.CAMBIO_MATERIA;

    }


}
