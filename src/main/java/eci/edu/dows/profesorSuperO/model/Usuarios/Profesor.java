package eci.edu.dows.profesorSuperO.model.Usuarios;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Profesor")
public class Profesor extends Usuario {

    public Profesor(String usuario, Permisos permiso, String correo, String Id) {
        super(usuario, permiso, correo, Id);
    }

    public Profesor() {}

}
