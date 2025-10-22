package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.CalendarioAcademicoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.FacultadDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.EstudianteDTO;

import java.util.List;

public interface DecanaturaService {

    SolicitudDTO cambiarEstado(String solicitudId, String accion);

    List<SolicitudDTO> obtenerSolicitudesPorFacultad(FacultadDTO facultadDTO);

    List<SolicitudDTO> obtenerSolicitudesPorPrioridad(int prioridad);

    List<SolicitudDTO> obtenerSolicitudesPendientes(FacultadDTO facultadDTO);

    EstudianteDTO verInformacionEstudiante(String estudianteId);

    List<SolicitudDTO> obtenerSolicitudesPorOrdenFacultad(String idFacultad);

    CalendarioAcademicoDTO createAcademicCalendar(CalendarioAcademicoDTO calendarioAcademicoDTO);

    CalendarioAcademicoDTO updateFinalDay(String finalDay, String id);

    CalendarioAcademicoDTO updateStartDay(String startDay, String id);
}
