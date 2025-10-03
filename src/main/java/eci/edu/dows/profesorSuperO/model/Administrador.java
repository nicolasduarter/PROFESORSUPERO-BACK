package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Administrador")
public class Administrador extends Usuario {
    public Administrador(String usuario, Permisos permiso, String correo, String Id) {
        super(usuario, permiso, correo, Id);
    }
}
