package eci.edu.dows.profesorSuperO.model;
import eci.edu.dows.profesorSuperO.model.Enums.Permisos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;



@Getter
@Setter
@Document("Estudiante")
public class Estudiante extends Usuario {


    private int semestre;
    private ArrayList<Horario> horarios;
    @DBRef
    private Facultad facultad;
    private Semaforo semaforo;

    public Estudiante(String usuario, Permisos permiso, String correo, String Id, Facultad facultad, int semestre) {
        super(usuario, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.facultad = facultad;
        this.semaforo = new Semaforo();
    }

    public  Estudiante(){}

}