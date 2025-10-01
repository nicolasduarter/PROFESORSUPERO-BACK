package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Decanatura")
public class Decanatura extends Usuario {

    private Facultad facultad;


    public Decanatura(String usuario, Permisos permiso, String correo, String id, Facultad facultad) {
        super(usuario, permiso, correo, id);
        this.facultad = facultad;
    }


}

