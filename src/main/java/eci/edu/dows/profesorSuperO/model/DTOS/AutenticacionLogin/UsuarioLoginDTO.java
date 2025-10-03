package eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDTO {
    private String codigo;
    private String rol;
}
