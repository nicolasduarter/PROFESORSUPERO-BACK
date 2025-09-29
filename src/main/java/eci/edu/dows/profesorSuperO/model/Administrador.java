package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document("Administrador")
public class Administrador extends Usuario {
    public Administrador(String usuario, String clave, String permiso, String correo, String Id) {
        super(usuario, clave, permiso, correo, Id);
    }
}
