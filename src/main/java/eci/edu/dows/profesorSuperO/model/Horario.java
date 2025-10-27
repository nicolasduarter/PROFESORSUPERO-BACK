package eci.edu.dows.profesorSuperO.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
    private int semestre;
    private List<Grupo> grupos;
}
