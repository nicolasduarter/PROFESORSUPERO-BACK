package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDecisionDTO {
    private String id;
    private EstadoSolicitud estadoAnterior;
    private EstadoSolicitud estadoNuevo;
    private String comentario;
    private String usuario;
    private LocalDateTime fechaDecision;

}