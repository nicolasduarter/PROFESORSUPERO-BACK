package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Clase")
public class Clase {
    @Id
    private String idClase;
    private String franjaHoraria;
    private String salon;
    private Profesor profesor;
    private String estado;
    private ArrayList<Estudiante> estudiantes;

    public Clase(String idClase,String franjaHoraria, Profesor profesor, String salon,String estado) {
        this.idClase = idClase;
        this.franjaHoraria = franjaHoraria;
        this.salon = salon;
        this.profesor = profesor;
        this.estado = estado;
        estudiantes = new ArrayList<>();
    }
    public String getIdClase() {
        return idClase;
    }
    public void setIdClase(String idClase) {
        this.idClase = idClase;
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

    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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
}
