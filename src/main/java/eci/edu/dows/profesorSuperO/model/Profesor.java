package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document("Profesor")
public class Profesor extends Usuario {
    private List<Grupo> grupos;

    public Profesor(String usuario, String clave, String permiso, String correo, String Id, List<Grupo> grupos) {
        super(usuario, clave, permiso, correo, Id);
        this.grupos = grupos;
    }

    public Profesor() {}
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
