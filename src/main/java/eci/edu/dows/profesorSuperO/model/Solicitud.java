package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValida;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValidaCambioGrupo;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;


@SolicitudValida
@Document("Solicitud")
public abstract class Solicitud {

    @Id
    private String id;
    @Indexed
    private EstadoSolicitud estado;
    @DBRef
    private Estudiante estudiante;
    private String motivo;
    @Indexed
    private LocalDate fecha;
    private int prioridad;
    @Transient
    private ArrayList<PeriodoObserver> observadores;
    private Facultades facultad;
    @DBRef
    private CalendarioAcademico calendarioAcademico;

    public Solicitud(String id, Estudiante estudiante, String motivo, LocalDate fecha) {
        this.id = id;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.estudiante = estudiante;
        this.motivo = motivo;
        this.fecha = fecha;
        this.prioridad = 0;
        this.facultad = estudiante.getFacultad();
    }

    public Solicitud() {}

    public Facultad getFacultadOBJ() {
        return estudiante.getFacultadObjeto();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
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

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ArrayList<PeriodoObserver> getObservadores() {
        return observadores;
    }

    public void setObservadores(ArrayList<PeriodoObserver> observadores) {
        this.observadores = observadores;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }

    public CalendarioAcademico getCalendarioAcademico() {
        return calendarioAcademico;
    }

    public void setCalendarioAcademico(CalendarioAcademico calendarioAcademico) {
        this.calendarioAcademico = calendarioAcademico;
    }
}