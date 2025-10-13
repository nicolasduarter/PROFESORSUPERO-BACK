package eci.edu.dows.profesorSuperO.model;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Document("historial_decisiones")
public class HistorialDecision {

    @Id
    private String id;

    @DBRef
    private Solicitud solicitud;

    private EstadoSolicitud estadoAnterior;
    private EstadoSolicitud estadoNuevo;
    private String comentario;
    private String usuario;
    private LocalDateTime fechaDecision;

    public HistorialDecision() {
        this.fechaDecision = LocalDateTime.now();
    }

    public HistorialDecision(Solicitud solicitud, EstadoSolicitud estadoAnterior,
                             EstadoSolicitud estadoNuevo, String comentario, String usuario) {
        this();
        this.solicitud = solicitud;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.comentario = comentario;
        this.usuario = usuario;
    }
}