package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Materia")
public class Materia {

    @Id
    private String id;
    @Indexed
    private String nombre;
    @Indexed
    private String estado;
    private ArrayList<Grupo> grupos;


    public Materia(String id, String nombre, ArrayList<Grupo> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.grupos = grupos;
        this.estado = "sin cursar";
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }
    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
