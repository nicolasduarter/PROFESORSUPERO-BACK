package eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCambioMateriaDTO extends SolicitudDTO {

    @NotNull(message = "El ID de la materia problema es obligatorio")
    private String materiaProblemaId;

    @NotNull(message = "El ID de la nueva materia es obligatorio")
    private String materiaCambioId;

    @NotNull(message = "El ID del grupo actual es obligatorio")
    private String grupoId;

    @NotNull(message = "El ID del nuevo grupo es obligatorio")
    private String grupoCambioId;

    @Override
    public String getTipoSolicitud() {
        return "CAMBIO_MATERIA";
    }

}