package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {
    private String usuario;
    private String clave;
    private String permiso;
    private String correo;
    private String id;
    private List<Grupo> grupos;

    public ProfesorDTO(String usuario, String clave, String permiso, String correo, String id, List<Grupo> grupos) {
        this.usuario = usuario;
        this.clave = clave;
        this.permiso = permiso;
        this.correo = correo;
        this.id = id;
        this.grupos = grupos;
    }


}
