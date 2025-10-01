package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Materia;

import java.util.List;

public class MateriaDTO {
    private String id;
    private String nombre;
    private Boolean estado;
    private int  creditos;
    private List<Materia> prerequisitos;
}
