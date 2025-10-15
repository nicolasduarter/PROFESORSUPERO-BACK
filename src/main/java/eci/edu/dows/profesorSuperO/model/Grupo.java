package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Observer.GruposObserver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
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
    @DBRef
    private ArrayList<Estudiante> listaEspera;
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
        this.listaEspera = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.materia = materia;
        this.cuposMax = cuposMax;
    }
}