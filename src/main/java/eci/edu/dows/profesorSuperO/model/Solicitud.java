package eci.edu.dows.profesorSuperO.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;

public abstract class Solicitud {
    @Id
    private String id;
    @DBRef
    private Estudiante estudiante;
    @Indexed
    private EstadoSolicitud estado;
    private Materia materiaProblema;
    private Materia materiaCambio;
    private Grupo grupo;
    private String motivo;
    @Indexed
    private String fecha;
    @Indexed
    private int prioridad;
    @Transient
    private ArrayList<PeriodoObserver> observadores;

    public Solicitud(String id, Materia materiaProblema, Materia materiaCambio,
                     Grupo grupo, String motivo, String fecha) {
        this.id = id;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.materiaProblema = materiaProblema;
        this.materiaCambio = materiaCambio;
        this.grupo = grupo;
        this.motivo = motivo;
        this.fecha = fecha;
        this.prioridad = 0;
        this.observadores = new ArrayList<>();
    }

    public String getId() { return id; }
    public EstadoSolicitud getEstado() { return estado; }
    public Materia getMateriaProblema() { return materiaProblema; }
    public Materia getMateriaCambio() { return materiaCambio; }
    public Grupo getGrupo() { return grupo; }
    public String getMotivo() { return motivo; }
    public String getFecha() { return fecha; }
    public int getPrioridad() { return prioridad; }
    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    public void agregarObservador(PeriodoObserver observador) { observadores.add(observador) ; }
}
