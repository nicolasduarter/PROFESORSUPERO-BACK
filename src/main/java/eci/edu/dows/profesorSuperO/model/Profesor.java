package eci.edu.dows.profesorSuperO.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Document("Profesor")
public class Profesor extends Usuario {

    public Profesor(String usuario, String clave, String permiso, String correo, String Id) {
        super(usuario, clave, permiso, correo, Id);
    }

    public Profesor() {}

}
