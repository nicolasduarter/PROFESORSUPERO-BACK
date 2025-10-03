package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.*;
import eci.edu.dows.profesorSuperO.model.DTOS.*;
import eci.edu.dows.profesorSuperO.model.DTOS.SolicitudesDTO.SolicitudDTO;
import eci.edu.dows.profesorSuperO.model.DTOS.UsuariosDTO.ProfesorDTO;
import eci.edu.dows.profesorSuperO.service.ConsultasService;
import eci.edu.dows.profesorSuperO.Util.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consultas")
public class ConsultasController {

    private final ConsultasService consultasService;
    private final HorarioMapper horarioMapper;
    private final SolicitudMapper solicitudMapper;
    private final SemaforoMapper semaforoMapper;
    private final ProfesorMapper profesorMapper;
    private final GrupoMapper grupoMapper;

    public ConsultasController(ConsultasService consultasService,
                               HorarioMapper horarioMapper,
                               SolicitudMapper solicitudMapper,
                               SemaforoMapper semaforoMapper,
                               ProfesorMapper profesorMapper,
                               GrupoMapper grupoMapper) {
        this.consultasService = consultasService;
        this.horarioMapper = horarioMapper;
        this.solicitudMapper = solicitudMapper;
        this.semaforoMapper = semaforoMapper;
        this.profesorMapper = profesorMapper;
        this.grupoMapper = grupoMapper;
    }

    @GetMapping("/estudiante/{estudianteId}/ultimoHorario")
    public HorarioDTO consultarUltimoHorarioEstudiante(@PathVariable String estudianteId) {
        Horario horario = consultasService.consultarUltimoHorarioEstudiante(estudianteId);
        return horario != null ? horarioMapper.toDTO(horario) : null;
    }

    @GetMapping("/estudiante/{estudianteId}/horarios")
    public List<HorarioDTO> consultarHorariosEstudiante(@PathVariable String estudianteId) {
        List<Horario> horarios = consultasService.consultarHorariosEstudiante(estudianteId);
        return horarios != null ? horarios.stream()
                .map(horarioMapper::toDTO)
                .collect(Collectors.toList()) : null;
    }

    @GetMapping("/estudiante/{estudianteId}/solicitudes")
    public List<SolicitudDTO> consultarSolicitudesEstudiante(@PathVariable String estudianteId) {
        return consultasService.consultarSolicitudesEstudiante(estudianteId)
                .stream()
                .map(solicitudMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/estudiante/{estudianteId}/semaforo")
    public SemaforoDTO consultarSemaforoEstudiante(@PathVariable String estudianteId) {
        Semaforo semaforo = consultasService.consultarSemaforoEstudiante(estudianteId);
        return semaforo != null ? semaforoMapper.toDTO(semaforo) : null;
    }

    @GetMapping("/grupo/{grupoId}/profesor")
    public ProfesorDTO consultarProfesorGrupo(@PathVariable String grupoId) {
        Profesor profesor = consultasService.consultarProfesorGrupo(grupoId);
        return profesor != null ? profesorMapper.toDTO(profesor) : null;
    }

    @GetMapping("/grupo/{grupoId}/cupos")
    public int consultarCuposGrupo(@PathVariable String grupoId) {
        return consultasService.consultarCuposGrupo(grupoId);
    }

    @GetMapping("/grupo/{grupoId}")
    public GrupoDTO obtenerGrupo(@PathVariable String grupoId) {
        Grupo grupo = consultasService.obtenerGrupo(grupoId);
        return grupo != null ? grupoMapper.toDTO(grupo) : null;
    }
}


