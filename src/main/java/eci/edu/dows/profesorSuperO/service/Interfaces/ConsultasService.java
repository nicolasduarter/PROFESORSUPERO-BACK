package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.HorarioDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SemaforoDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface ConsultasService {

    HorarioDTO consultarUltimoHorarioEstudiante(String estudianteId);

    List<HorarioDTO> consultarHorariosEstudiante(String estudianteId);

    SolicitudDTO consultarSolicitudPorEstudiante(String idStudent, String idSolicitud);

    List<SolicitudDTO> consultarSolicitudesEstudiante(String estudianteId);

    SemaforoDTO consultarSemaforoEstudiante(String estudianteId);

    Optional<ProfesorDTO> consultarProfesorGrupo(String grupoId);

    int consultarCuposGrupo(String grupoId);

    GrupoDTO obtenerGrupo(String id);
}
