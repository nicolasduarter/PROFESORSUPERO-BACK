package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Document("Profesor")
public class Profesor extends Usuario {

    public Profesor(String usuario, String clave, Permisos permiso, String correo, String Id) {
        super(usuario, clave, permiso, correo, Id);
    }

    public Profesor() {}

}
