package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValidaCambioGrupo;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolicitudValidaCambioGrupo
public class SolicitudCambioGrupoDTO extends SolicitudDTO {

    @NotNull(message = "La materia no puede ser vacía")
    private MateriaDTO materiaProblema;

    @NotNull(message = "El grupo actual no puede ser vacío")
    private GrupoDTO grupo;

    @NotNull(message = "El nuevo grupo no puede ser vacío")
    private GrupoDTO grupoCambio;

    public String getTipo() {
        return "CAMBIO_GRUPO";
    }

}
