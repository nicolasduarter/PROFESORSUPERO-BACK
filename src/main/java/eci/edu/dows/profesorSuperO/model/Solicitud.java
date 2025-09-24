package eci.edu.dows.profesorSuperO.model;

import java.util.ArrayList;

public abstract class Solicitud {
    private String id;
    private Estudiante estudiante;
    private String estado;
    private Materia materiaProblema;
    private Materia materiaCambio;
    private Grupo grupo;
    private String motivo;
    private String fecha;
    private int prioridad;
    private ArrayList<PeriodoObserver> observadores;

    public Solicitud(String id, Materia materiaProblema, Materia materiaCambio,
                     Grupo grupo, String motivo, String fecha) {
        this.id = id;
        this.estado = "Pendiente";
        this.materiaProblema = materiaProblema;
        this.materiaCambio = materiaCambio;
        this.grupo = grupo;
        this.motivo = motivo;
        this.fecha = fecha;
        this.prioridad = 0;
        this.observadores = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getEstado() { return estado; }
    public Materia getMateriaProblema() { return materiaProblema; }
    public Materia getMateriaCambio() { return materiaCambio; }
    public Grupo getGrupo() { return grupo; }
    public String getMotivo() { return motivo; }
    public String getFecha() { return fecha; }
    public int getPrioridad() { return prioridad; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    public void agregarObservador(PeriodoObserver observador) { observadores.add(observador) ; }
}
