package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import eci.edu.dows.profesorSuperO.model.Enums.Facultades;
import eci.edu.dows.profesorSuperO.model.Observer.PeriodoObserver;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValida;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
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
    private Facultad facultad;
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


}