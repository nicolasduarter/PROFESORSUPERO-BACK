package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.MateriaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCambioMateriaDTO extends SolicitudDTO {

    @NotNull(message = "La materia no puede ser vacía")
    private MateriaDTO materiaProblema;

    @NotNull(message = "La nueva materia no puede ser vacía")
    private MateriaDTO materiaCambio;

    @NotNull(message = "El grupo no puede ser vacío")
    private GrupoDTO grupo;

    @NotNull(message = "El nuevo grupo no puede ser vacío")
    private GrupoDTO grupoCambio;

    public String getTipo() {
        return "CAMBIO_MATERIA";
    }

}
