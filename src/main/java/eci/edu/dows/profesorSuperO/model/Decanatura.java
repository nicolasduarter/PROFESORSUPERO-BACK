package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document("Decanatura")
public class Decanatura extends Usuario {

    private Facultad facultad;


    public Decanatura(String usuario, Permisos permiso, String correo, String id, Facultad facultad) {
        super(usuario, permiso, correo, id);
        this.facultad = facultad;
    }


}

