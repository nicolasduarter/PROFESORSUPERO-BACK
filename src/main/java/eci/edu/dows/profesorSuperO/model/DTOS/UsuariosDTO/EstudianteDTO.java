package eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class EstudianteDTO extends UsuarioDTO{
    private FacultadDTO facultad;
    private int semestre;
}