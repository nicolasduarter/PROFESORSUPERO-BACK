package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Materia;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCambioMateriaDTO extends SolicitudDTO {
    @NotNull(message = "Materria no puede ser vacia")
    private Materia materiaProblema;
    @NotNull(message = "Materria nueva no puede ser vacia")
    private Materia materiaCambio;
    @NotNull(message = "El  grupo  no puede ser vacia")
    private Grupo grupo;
    @NotNull(message = "El nuevo grupo  no puede ser vacia")
    private Grupo grupoCambio;

}