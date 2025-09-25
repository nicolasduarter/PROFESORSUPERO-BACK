package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@TypeAlias("solicitudCambioMateria")
public class SolicitudCambioMateria extends  Solicitud {
    @DBRef
    private Materia materiaProblema;
    @DBRef
    private Materia materiaCambio;
    private Grupo grupo;
    private Grupo grupoCambio;

    public SolicitudCambioMateria(String id, Estudiante estudiante, String motivo, LocalDate fecha,
                                  Materia materiaProblema, Materia materiaCambio, Grupo grupo, Grupo grupoCambio,Facultades facultad) {
        super(id, estudiante, motivo, fecha,facultad);
        this.materiaProblema = materiaProblema;
        this.materiaCambio = materiaCambio;
        this.grupo = grupo;
        this.grupoCambio = grupoCambio;
    }

    public Materia getMateriaProblema() {
        return materiaProblema;
    }

    public void setMateriaProblema(Materia materiaProblema) {
        this.materiaProblema = materiaProblema;
    }

    public Materia getMateriaCambio() {
        return materiaCambio;
    }

    public void setMateriaCambio(Materia materiaCambio) {
        this.materiaCambio = materiaCambio;
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
}
