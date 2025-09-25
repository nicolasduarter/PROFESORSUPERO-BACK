package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;

public class Facultad {
    private ArrayList<Materia> materias;
    private Facultades facultad;
    public Facultad(Facultades facultad) {
        this.facultad = facultad;
        this.materias = new ArrayList<>();

    }

    public void setMateria(Materia materia) {
        this.materias.add(materia);
    }


    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }
}
