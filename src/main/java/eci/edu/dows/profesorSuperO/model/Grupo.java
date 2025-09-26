package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document("Grupo")
public class Grupo {
    @Id
    private String idGrupo;
    @Indexed
    private String nombre;
    @DBRef
    private Profesor profesor;
    private int cupo;
    @DBRef
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Clase> clases;
    @Transient
    private ArrayList<GruposObserver> observadores;



    public Grupo(String idGrupo, String nombre, Profesor profesor,int cupo) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.cupo = cupo;
        this.estudiantes = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getCupo() {
        return cupo;
    }
    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    public void agregarEstudiantes(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
    public void eliminarEstudiantes(Estudiante estudiante) {
        estudiantes.remove(estudiante);
    }
    public ArrayList<Clase> getClases() {
        return clases;
    }
    public void agregarClases(Clase clase) {
        clases.add(clase);
    }
    public ArrayList<GruposObserver> getObservadores(){
        return observadores;
    }
    public void agregarObservador(GruposObserver observador){
        observadores.add(observador);
    }

}