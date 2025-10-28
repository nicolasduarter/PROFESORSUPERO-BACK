package eci.edu.dows.profesorSuperO.model.DTOS;

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
public class SoliGrupoTestDTO {
    private String id;
    private String motivo;
    private LocalDate fecha;
    private int prioridad;
    private String infoAdicionalEstudiante;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private String estudianteId;

    @NotNull(message = "El ID de la materia problema es obligatorio")
    private String materiaProblemaId;

    @NotNull(message = "El ID del grupo actual es obligatorio")
    private String grupoId;

    @NotNull(message = "El ID del nuevo grupo es obligatorio")
    private String grupoCambioId;

    // Este campo se establece directamente
    public String getTipoSolicitud() {
        return "CAMBIO_GRUPO";
    }
}
