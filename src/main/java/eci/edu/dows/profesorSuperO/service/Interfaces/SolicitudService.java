package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.HistorialDecisionDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;

import java.util.List;

public interface SolicitudService {

    SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(SolicitudCambioGrupoDTO dto);

    SolicitudCambioMateriaDTO crearSolicitudCambioMateria(SolicitudCambioMateriaDTO dto);

    List<SolicitudDTO> consultarSolicitudes();

    SolicitudDTO consultarSolicitudPorId(String id);

    List<HistorialDecisionDTO> consultarHistorialDecisiones(String solicitudId);

    SolicitudDTO actualizarEstadoSolicitud(String id, EstadoSolicitud nuevoEstado, String comentario, String usuario);

    void eliminarSolicitud(String id);

    SolicitudDTO agregarInformacionAdicional(String solicitudId, String texto);
}
