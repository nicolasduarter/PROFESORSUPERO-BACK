package eci.edu.dows.profesorSuperO.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;



@Getter
@Setter
@Document("Estudiante")
public class Estudiante extends Usuario {


    private int semestre;
    private ArrayList<Horario> horarios;
    private Facultad facultad;
    private Semaforo semaforo;

    public Estudiante(String usuario, String clave, String permiso, String correo, String Id, Facultad facultad, int semestre) {
        super(usuario, clave, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.facultad = facultad;
        this.semaforo = new Semaforo();
    }

    public  Estudiante(){}



}