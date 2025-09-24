package eci.edu.dows.profesorSuperO.model;

import java.util.List;

public class Horario {
    private int semestre;
    private List<Grupo> grupos;

    public Horario(int semestre, List<Grupo> grupos) {
        this.semestre = semestre;
        this.grupos = grupos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
