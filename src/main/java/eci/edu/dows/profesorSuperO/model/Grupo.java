package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Observer.GruposObserver;
import eci.edu.dows.profesorSuperO.model.Usuarios.Estudiante;
import eci.edu.dows.profesorSuperO.model.Usuarios.Profesor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Document("Grupo")
public class Grupo {
    @Id
    private String id;
    @Indexed
    private String nombre;
    @DBRef
    private Profesor profesor;
    private int cupo;

    @DBRef(lazy = false)
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    @DBRef(lazy = false)
    private ArrayList<Estudiante> listaEspera = new ArrayList<>();
    private ArrayList<Clase> clases = new ArrayList<>();
    @Transient
    private ArrayList<GruposObserver> observadores = new ArrayList<>();

    private int cuposMax;

    @DBRef(lazy = false)
    private Materia materia;

    public Grupo(String id, String nombre, Profesor profesor, int cupo, Materia materia, int cuposMax) {
        this.id = id;
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
