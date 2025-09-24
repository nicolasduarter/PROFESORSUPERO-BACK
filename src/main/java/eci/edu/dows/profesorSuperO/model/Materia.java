package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;

public class Materia {
    private String nombre;
    private ArrayList<Grupo> grupos;
    private ArrayList<String> horario;
    private int cupos;
    private String estado;

    public Materia(String nombre, ArrayList<Grupo> grupos, ArrayList<String> horario, int cupos) {
        this.nombre = nombre;
        this.grupos = grupos;
        this.horario = horario;
        this.cupos = cupos;
        this.estado = "no cursada";
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }
    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }
    public ArrayList<String> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<String> horario) {
        this.horario = horario;
    }
    public int getCupos() {
        return cupos;
    }
    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
