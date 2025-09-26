package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Materia")
public class Materia {

    @Id
    private String id;
    @Indexed
    private String nombre;
    @Indexed
    private Boolean estado;
    private ArrayList<Grupo> grupos;
    private int  creditos;
    private List<Materia> prerequisitos;


    public Materia() {}

    public Materia(String id, String nombre, ArrayList<Grupo> grupos, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.grupos = grupos;
        this.estado = false;
        this.creditos = creditos;
        this.prerequisitos = new ArrayList<Materia>();
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
    public boolean getEstado() {
        return estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Materia> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Materia> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }
}
