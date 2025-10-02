package eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SolicitudValida
public  class SolicitudDTO {

    private String id;
    private String estado;
    private EstudianteDTO estudiante;
    private String motivo;
    private LocalDate fecha;
    private int prioridad;
    private String tipoSolicitud;
}
