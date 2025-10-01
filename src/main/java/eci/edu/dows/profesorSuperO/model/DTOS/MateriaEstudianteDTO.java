package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaEstudianteDTO {
    private MateriaDTO materia;
    private EstudianteDTO estudiante;
    private String estado;
    private int intentos;
    private double nota;
    private LocalDate fechaAprobada;
}
