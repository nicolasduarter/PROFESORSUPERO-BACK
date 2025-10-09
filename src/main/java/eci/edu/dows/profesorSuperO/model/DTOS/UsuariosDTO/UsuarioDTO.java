package eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class UsuarioDTO {
    private String id;
    private String correo;
    private String fullName;
    private String permiso;
}
