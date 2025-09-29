package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Materia;
import eci.edu.dows.profesorSuperO.model.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private String idGrupo;
    private String nombre;
    private Profesor profesor;
    private int cupo;
    private Materia materia;
    private int cuposMax;


}
