package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.SoliGrupoTestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SoliMateriaTestDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.HistorialDecisionDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioGrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudCambioMateriaDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.Request.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.Enums.EstadoSolicitud;

import java.util.List;

public interface SolicitudService {

    SolicitudCambioGrupoDTO crearSolicitudCambioGrupo(SoliGrupoTestDTO dto);


    SolicitudCambioMateriaDTO crearSolicitudCambioMateria(SoliMateriaTestDTO dto);


    List<SolicitudDTO> consultarSolicitudes();

    SolicitudDTO consultarSolicitudPorId(String id);

    List<HistorialDecisionDTO> consultarHistorialDecisiones(String solicitudId);

    SolicitudDTO actualizarEstadoSolicitud(String id, EstadoSolicitud nuevoEstado, String comentario, String usuario);

    void eliminarSolicitud(String id);

    SolicitudDTO agregarInformacionAdicional(String solicitudId, String texto);
}
