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
    private boolean estado;
    private ArrayList<Grupo> grupos;
    private int cupos;


    public Materia(String id, String nombre, ArrayList<Grupo> grupos, int cupos) {
        this.id = id;
        this.nombre = nombre;
        this.grupos = grupos;
        this.cupos = cupos;
        this.estado = false;
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
    public int getCupos() {
        return cupos;
    }
    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }
}
