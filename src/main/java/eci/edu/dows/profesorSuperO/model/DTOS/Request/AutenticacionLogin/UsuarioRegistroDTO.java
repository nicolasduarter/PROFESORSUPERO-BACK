package eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin;


import eci.edu.dows.profesorSuperO.model.DTOS.Request.FacultadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {
    private String correo;
    private String fullName;
    private String permiso;
    private FacultadDTO facultad;
    private int semestre;
    private String contra;
}
