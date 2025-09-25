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
    @DBRef
    private ArrayList<Grupo>  grupos;
    private Facultades facultad;
    private Facultad facultadObjeto;
    @DBRef
    private Semaforo semaforo;

    public Estudiante(String usuario, String clave, String permiso, String correo, String Id, Facultad facultadObjeto, int semestre,Semaforo semaforo) {
        super(usuario, clave, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.facultad = facultadObjeto.getFacultad();
        this.facultadObjeto = facultadObjeto;
        this.semaforo = semaforo;
        this.grupos = new ArrayList<>();
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Facultad getFacultadObjeto() {
        return facultadObjeto;
    }

    public void setFacultadObjeto(Facultad facultadObjeto) {
        this.facultadObjeto = facultadObjeto;
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

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }
}