package eci.edu.dows.profesorSuperO.model;

import java.util.List;
import lombok.Getter;

@Getter
public class Horario {
    private int semestre;
    private List<Grupo> grupos;

    public Horario(int semestre, List<Grupo> grupos) {
        this.semestre = semestre;
        this.grupos = grupos;
    }


}
