package eci.edu.dows.profesorSuperO.model.DTOS.AutenticacionLogin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroSalidaDTO {
    String id;
    String usuario;
    String rol;
}
