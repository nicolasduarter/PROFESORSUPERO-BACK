package eci.edu.dows.profesorSuperO.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;




@Document("Estudiante")
public class Estudiante extends Usuario {



    private int semestre;

    private ArrayList<Horario> horarios;
    private ArrayList<Solicitud> solicitudes;
    private ArrayList<Grupo>  grupos;
    private String facultad;

    @DBRef
    private Semaforo semaforo;

    public Estudiante(String usuario, String clave, String permiso, String correo, String Id, String facultad, int semestre,Semaforo semaforo) {
        super(usuario, clave, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.facultad = facultad;
        this.semaforo = semaforo;
        this.grupos = new ArrayList<>();
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }
    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }
}