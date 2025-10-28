package eci.edu.dows.profesorSuperO.model.DTOS.Request.AutenticacionLogin;


import eci.edu.dows.profesorSuperO.model.Facultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDTO {
    private String id;
    private String usuario;
    private String rol;
    private Facultad facultad;
}
