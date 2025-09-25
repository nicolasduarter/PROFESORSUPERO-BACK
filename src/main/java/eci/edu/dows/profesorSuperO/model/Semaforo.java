package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;

public class Semaforo {
    private ArrayList<Materia> materias;
    private Estudiante estudiante;
    private int creditosTotales;
    private int creditosActuales;
    private int creditosFaltantes;
    private Facultad facultad;
    private int materiasVistas;
    private int promedio;

    public Semaforo(Estudiante estudiante) {
        this.materias = new ArrayList<>();
    }


    public ArrayList<Materia> getMateriasNoCursadas() {
        ArrayList<Materia> noCursadas = new ArrayList<>();
        for (Materia materia : materias) {
            if (!materia.getEstado()) {
                noCursadas.add(materia);
            }
        }
        return noCursadas;
    }

    public ArrayList<Materia> getMateriasCursadas() {
        ArrayList<Materia> cursadas = new ArrayList<>();
        for (Materia materia : materias) {
            if (materia.getEstado()) {
                cursadas.add(materia);
            }
        }
        return cursadas;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materia getMateriaById(String id) {
        for (Materia m : materias) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public boolean cambiarEstadoDeMateria(String id, boolean nuevoEstado) {
        for (Materia m : materias) {
            if (m.getId().equals(id)) {
                m.setEstado(nuevoEstado);
                return true;
            }
        }
        return false;
    }


}
