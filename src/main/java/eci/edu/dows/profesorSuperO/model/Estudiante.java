package eci.edu.dows.profesorSuperO.model;
import java.util.ArrayList;

public class Estudiante extends Usuario {
    private int semestre;
    private ArrayList<Horario> horarios;
    private ArrayList<Solicitud> solicitudes;
    private String Facultad;

    public Estudiante(String usuario, String clave, String permiso, String correo, String Id, String facultad, int semestre) {
        super(usuario, clave, permiso, correo, Id);
        this.semestre = semestre;
        this.horarios = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.Facultad = facultad;
    }


    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getFacultad() {
        return Facultad;
    }

    public void setFacultad(String facultad) {
        this.Facultad = facultad;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }
}