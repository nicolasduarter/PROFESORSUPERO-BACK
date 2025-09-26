package eci.edu.dows.profesorSuperO.model.DTOS;

import eci.edu.dows.profesorSuperO.model.Estudiante;
import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.model.Materia;

import java.time.LocalDate;

public class SolicitudCambioGrupoDTO {
    private String id;
    private Estudiante estudiante;
    private String motivo;
    private LocalDate fecha;
    private Materia materiaProblema;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Materia getMateriaProblema() {
        return materiaProblema;
    }

    public void setMateriaProblema(Materia materiaProblema) {
        this.materiaProblema = materiaProblema;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getGrupoCambio() {
        return grupoCambio;
    }

    public void setGrupoCambio(Grupo grupoCambio) {
        this.grupoCambio = grupoCambio;
    }

    private Grupo grupo;
    private Grupo grupoCambio;
}
