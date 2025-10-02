package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;

import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValidaCambioGrupo;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolicitudValidaCambioGrupo
public class SolicitudCambioGrupoDTO extends SolicitudDTO {
    @NotNull(message = "Materria no puede ser vacia")
    private MateriaDTO materiaProblema;
    @NotNull(message = "Grupo no puede ser vacia")
    private GrupoDTO grupo;
    @NotNull(message = "El nuevo grupo  no puede ser vacia")
    private GrupoDTO grupoCambio;
}
