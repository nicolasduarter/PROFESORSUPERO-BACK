package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCambioMateriaDTO {
    private String id;
    private Estudiante estudiante;
    private String motivo;
    private LocalDate fecha;
    private Materia materiaProblema;
    private Materia materiaCambio;
    private Grupo grupo;
    private Grupo grupoCambio;


}
