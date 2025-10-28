package eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.UsuariosDTO.EstudianteDTO;
import eci.edu.dows.profesorSuperO.service.Validadores.SolicitudValida;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter

@SolicitudValida
public class SolicitudDTO {

    private String id;
    private String estado;
    private String estudianteId;
    private String motivo;
    private LocalDate fecha;
    private int prioridad;
    private String tipoSolicitud;
    private String infoAdicionalEstudiante;

    private String nombreEstudiante;
    private String nombrePrograma;
    private String nombreMateriaProblema;
    private String nombreMateriaCambio;
    private String grupoActual;
    private String grupoNuevo;
    private String nombreFacultad;
}