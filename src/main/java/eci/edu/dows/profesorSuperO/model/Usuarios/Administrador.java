package eci.edu.dows.profesorSuperO.model.Usuarios;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@TypeAlias("Administrador")
public class Administrador extends Usuario {
    public Administrador(String usuario, Permisos permiso, String correo, String Id) {
        super(usuario, permiso, correo, Id);
    }
}
