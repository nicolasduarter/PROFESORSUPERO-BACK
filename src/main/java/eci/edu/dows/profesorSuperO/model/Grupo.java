package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;

public class Grupo {
    private String idGrupo;
    private String nombre;
    private Profesor profesor;
    private String franjaHoraria;
    private int cupo;
    private String estado;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<GruposObserver> observadores;

    public Grupo() {
        this.idGrupo = "";
        this.profesor = null;
        this.franjaHoraria = "";
        this.cupo = 0;
        this.estado = "";
        this.estudiantes = new ArrayList<>();
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

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
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

    public ArrayList<GruposObserver> getObservadores(){
        return observadores;
    }
    public void agregarObservador(GruposObserver observador){
        observadores.add(observador);
    }
}