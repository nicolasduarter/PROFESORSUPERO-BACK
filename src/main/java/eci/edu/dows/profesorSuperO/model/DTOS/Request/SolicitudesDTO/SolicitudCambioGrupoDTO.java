package eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.MateriaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCambioGrupoDTO extends SolicitudDTO {

    @NotNull(message = "El ID de la materia problema es obligatorio")
    private String materiaProblemaId;

    @NotNull(message = "El ID del grupo actual es obligatorio")
    private String grupoId;

    @NotNull(message = "El ID del nuevo grupo es obligatorio")
    private String grupoCambioId;

    @Override
    public String getTipoSolicitud() {
        return "CAMBIO_GRUPO";
    }

}