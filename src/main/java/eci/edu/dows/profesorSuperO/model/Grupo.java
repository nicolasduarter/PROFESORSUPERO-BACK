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
    @Indexed
    private int cupo;
    @Indexed
    private String estado;

    private int cuposMax;
    @DBRef
    private Profesor profesor;
    private String franjaHoraria;
    private String salon;
    @DBRef
    private ArrayList<Estudiante> estudiantes;
    @Transient
    private ArrayList<GruposObserver> observadores;


    public Grupo(String idGrupo, String nombre, Profesor profesor, String franjaHoraria, String salon,int cupo, String estado,int  cuposMax) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.franjaHoraria = franjaHoraria;
        this.salon = salon;
        this.cupo = cupo;
        this.estado = estado;
        this.estudiantes = new ArrayList<>();
        this.observadores = new ArrayList<>();
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

    public void setObservadores(ArrayList<GruposObserver> observadores) {
        this.observadores = observadores;
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

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    public String getSalon() {
        return salon;
    }
    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public ArrayList<GruposObserver> getObservadores(){
        return observadores;
    }
    public void agregarObservador(GruposObserver observador){
        observadores.add(observador);
    }
}