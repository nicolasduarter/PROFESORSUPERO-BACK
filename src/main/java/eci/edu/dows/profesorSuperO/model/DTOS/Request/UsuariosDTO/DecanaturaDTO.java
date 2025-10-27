package eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO;

import eci.edu.dows.profesorSuperO.model.Facultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecanaturaDTO extends UsuarioDTO {
    private Facultad facultad;
}
