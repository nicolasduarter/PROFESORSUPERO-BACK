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

    private int cuposMax;

    @DBRef
    private Materia materia;


    public Grupo(String idGrupo, String nombre, Profesor profesor,int cupo, Materia materia,int  cuposMax) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.cupo = cupo;
        this.estudiantes = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.materia = materia;
        this.cuposMax = cuposMax;
    }

    public int getCuposMax() {
        return cuposMax;
    }

    public void setCuposMax(int cuposMax) {
        this.cuposMax = cuposMax;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    public void setObservadores(ArrayList<GruposObserver> observadores) {
        this.observadores = observadores;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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