package eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EstudianteDTO extends UsuarioDTO{
    private FacultadDTO facultad;
    private int semestre;
}