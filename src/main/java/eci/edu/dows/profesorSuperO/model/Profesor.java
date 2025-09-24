package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
    private List<Grupo> grupos;

    public Profesor(String usuario, String clave, String permiso, String correo, String Id, List<Grupo> grupos) {
        super(usuario, clave, permiso, correo, Id);
        this.grupos = grupos;
    }
}
